
package main.java.by.bntu.poisit.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import main.java.by.bntu.poisit.entity.Account;
import main.java.by.bntu.poisit.entity.Order;
import main.java.by.bntu.poisit.entity.OrderItem;
import main.java.by.bntu.poisit.entity.Product;
import main.java.by.bntu.poisit.exception.AccessDeniedException;
import main.java.by.bntu.poisit.exception.ResourceNotFoundException;
import main.java.by.bntu.poisit.exception.ServerErrorException;
import main.java.by.bntu.poisit.form.ProductForm;
import main.java.by.bntu.poisit.jdbc.JDBCUtils;
import main.java.by.bntu.poisit.jdbc.ResultSetHandler;
import main.java.by.bntu.poisit.jdbc.ResultSetHandlerFactory;
import main.java.by.bntu.poisit.model.CurrentAccount;
import main.java.by.bntu.poisit.model.ShoppingCart;
import main.java.by.bntu.poisit.model.ShoppingCartItem;
import main.java.by.bntu.poisit.model.SocialAccount;
import main.java.by.bntu.poisit.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private static final ResultSetHandler<Product> productsResultSetHandler
            = ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);

    private static final ResultSetHandler<Account> accountResultSetHandler
            = ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ACCOUNT_RESULT_SET_HANDLER);

    private static final ResultSetHandler<Order> orderResultSetHandler
            = ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);

    private final ResultSetHandler<List<OrderItem>> orderItemListResultSetHandler
            = ResultSetHandlerFactory.getListResultHandler(ResultSetHandlerFactory.ORDER_ITEM_RESULT_SET_HANDLER);

    private final ResultSetHandler<List<Order>> ordersResultSetHandler
            = ResultSetHandlerFactory.getListResultHandler(ResultSetHandlerFactory.ORDER_RESULT_SET_HANDLER);

    private final ResultSetHandler<Integer> countResultSetHandler
            = ResultSetHandlerFactory.getCountResultSetHandler();

    private final DataSource dataSource;

    public OrderServiceImpl(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    @Override
    public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        try ( Connection c = dataSource.getConnection()) {
            Product product = JDBCUtils.select(c, "SELECT p.*, c.name as category, pr.name as producer FROM product p, producer"
                    + " pr, category c WHERE c.id = p.id_category AND pr.id = p.id_producer "
                    + " AND p.id=?",
                    productsResultSetHandler, productForm.getIdProduct());

            if (product == null) {
                throw new ServerErrorException("Product not found by id = " + productForm.getIdProduct());
            }
            shoppingCart.addProduct(product, productForm.getCount());
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }

    @Override
    public void removeProductFromShoppingCart(ProductForm form, ShoppingCart shoppingCart) {
        shoppingCart.removeProduct(form.getIdProduct(), form.getCount());
    }
    
    @Override
    public List<Account> listAllUsers() {
        try ( Connection c = dataSource.getConnection()) {
            List<Account> resultList = new ArrayList<>();
            String query = "SELECT * FROM account";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setEmail(rs.getString("email"));
                a.setRole(rs.getInt("role"));
                resultList.add(a);
            }
            return resultList;
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }

    }

    @Override
    public String serializeShoppingCart(ShoppingCart shoppingCart) {
        StringBuilder res = new StringBuilder();
        for (ShoppingCartItem item : shoppingCart.getItems()) {
            res.append(item.getProduct().getId()).append("-").append(item.getCount()).append("|");
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }

    @Override
    public ShoppingCart deserializeShoppingCart(String string) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = string.split("\\|");
        for (String item : items) {
            try {
                String data[] = item.split("-");
                int idProduct = Integer.parseInt(data[0]);
                int count = Integer.parseInt(data[1]);
                addProductToShoppingCart(new ProductForm(idProduct, count), shoppingCart);
            } catch (RuntimeException e) {
                LOGGER.error("Can't add product to ShoppingCart during deserialization: item=" + item, e);
            }
        }
        return shoppingCart.getItems().isEmpty() ? null : shoppingCart;
    }

    @Override
    public CurrentAccount authentificate(SocialAccount socialAccount) {
        try ( Connection c = dataSource.getConnection()) {
            Account account = JDBCUtils.select(c, "SELECT * FROM account WHERE email=?", accountResultSetHandler, socialAccount.getEmail());
            if (account == null) {
                account = new Account(socialAccount.getName(), socialAccount.getEmail());
                account = JDBCUtils.insert(c, "INSERT INTO account values (nextval('account_seq'),? , ? )",
                        accountResultSetHandler, account.getName(), account.getEmail());
                c.commit();
            }
            return account;
        } catch (SQLException ex) {
            throw new ServerErrorException("Can't execute SQL request: " + ex.getMessage(), ex);
        }
    }

    @Override
    public long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount) {
        if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
            throw new ServerErrorException("shoppingCart is null or empty");
        }
        try ( Connection c = dataSource.getConnection()) {
            Order order = JDBCUtils.insert(c, "insert into \"order\" values(nextval('order_seq'),?,?)", orderResultSetHandler,
                    currentAccount.getId(), new Timestamp(System.currentTimeMillis()));
            JDBCUtils.insertBatch(c, "insert into order_item values(nextval('order_item_seq'),?,?,?)",
                    toOrderItemParameterList(order.getId(), shoppingCart.getItems()));
            
            return order.getId();
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }

    private List<Object[]> toOrderItemParameterList(long idOrder, Collection<ShoppingCartItem> items) {
        List<Object[]> parametersList = new ArrayList<>();
        for (ShoppingCartItem item : items) {
            parametersList.add(new Object[]{idOrder, item.getProduct().getId(), item.getCount()});
        }
        return parametersList;
    }
    
    @Override
    public int findIdFromUser(Integer userID) {
        try ( Connection c = dataSource.getConnection()) {
            int id = 0;
            String sql = "SELECT id FROM account WHERE id = ?";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                id = res.getInt("id");
            }

            return id;

        } catch (SQLException ex) {
            throw new ServerErrorException("Can't execute SQL request: " + ex.getMessage(), ex);

        }

    }
    
    @Override
    public void deleteUserFromSystem(int id) {
        try ( Connection c = dataSource.getConnection()) {
            String sql = "DELETE FROM account WHERE id = ?  ";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new ServerErrorException("Can't execute SQL request: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Order findOrderById(long id, CurrentAccount currentAccount) {
        try ( Connection c = dataSource.getConnection()) {
            Order order = JDBCUtils.select(c, "SELECT * FROM \"order\" where id=?", orderResultSetHandler, id);
            if (order == null) {
                throw new ResourceNotFoundException("Order not found by id: " + id);
            }
            if (!order.getIdAccount().equals(currentAccount.getId())) {
                throw new AccessDeniedException("Account with id=" + currentAccount.getId() + " is not owner for order with id=" + id);
            }
            List<OrderItem> list = JDBCUtils.select(c,
                    "SELECT o.id AS oid, o.id_order AS id_order, o.id_product, o.count, p.*, c.name AS category, pr.name AS producer FROM order_item o, product p, category c, producer pr "
                    + "WHERE pr.id=p.id_producer AND c.id=p.id_category AND o.id_product=p.id AND o.id_order=?",
                    orderItemListResultSetHandler, id);
            order.setItems(list);
            return order;
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }

    }

    @Override
    public List<Order> listMyOrders(CurrentAccount currentAccount, int page, int limit) {
        int offset = (page - 1) * limit;
        try ( Connection c = dataSource.getConnection()) {
            List<Order> orders = JDBCUtils.select(c, "SELECT * FROM \"order\" WHERE id_account=? ORDER BY id DESC limit ? offset ?", ordersResultSetHandler, currentAccount.getId(), limit, offset);
            return orders;
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }

    @Override
    public int countMyOrders(CurrentAccount currentAccount) {
        try ( Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "SELECT count(*) FROM \"order\" WHERE id_account=?", countResultSetHandler, currentAccount.getId());
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }
}
