package main.java.by.bntu.poisit.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.by.bntu.poisit.entity.Account;
import main.java.by.bntu.poisit.entity.Category;
import main.java.by.bntu.poisit.entity.Order;
import main.java.by.bntu.poisit.entity.OrderItem;
import main.java.by.bntu.poisit.entity.Producer;
import main.java.by.bntu.poisit.entity.Product;

public class ResultSetHandlerFactory {

    public final static ResultSetHandler<Account> ACCOUNT_RESULT_SET_HANDLER = new ResultSetHandler<Account>() {
        @Override
        public Account handle(ResultSet rs) throws SQLException {
            Account a = new Account();
            a.setId(rs.getInt("id"));
            a.setEmail(rs.getString("email"));
            a.setName(rs.getString("name"));
            a.setRole(rs.getInt("role"));
            return a;
        }
    };

    public final static ResultSetHandler<OrderItem> ORDER_ITEM_RESULT_SET_HANDLER = new ResultSetHandler<OrderItem>() {
        @Override
        public OrderItem handle(ResultSet rs) throws SQLException {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(rs.getLong("oid"));
            orderItem.setCount(rs.getInt("count"));
            orderItem.setIdOrder(rs.getLong("id_order"));
            Product p = PRODUCT_RESULT_SET_HANDLER.handle(rs);
            orderItem.setProduct(p);
            return orderItem;
        }
    };

    public final static ResultSetHandler<Order> ORDER_RESULT_SET_HANDLER = new ResultSetHandler<Order>() {
        @Override
        public Order handle(ResultSet rs) throws SQLException {
            Order order = new Order();
            order.setId(rs.getLong("id"));
            order.setCreated(rs.getTimestamp("created"));
            order.setIdAccount(rs.getInt("id_account"));
            return order;
        }
    };

    public final static ResultSetHandler<Product> PRODUCT_RESULT_SET_HANDLER = new ResultSetHandler<Product>() {
        @Override
        public Product handle(ResultSet rs) throws SQLException {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            p.setImageLink(rs.getString("image_link"));
            p.setPrice(rs.getBigDecimal("price"));
            p.setCategory(rs.getString("category"));
            p.setProducer(rs.getString("producer"));
            return p;
        }

    };

    public final static ResultSetHandler<Category> CATEGORY_RESULT_SET_HANDLER = new ResultSetHandler<Category>() {
        @Override
        public Category handle(ResultSet rs) throws SQLException {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setUrl(rs.getString("url"));
            c.setProductCount(rs.getInt("product_count"));
            return c;
        }

    };

    public final static ResultSetHandler<Producer> PRODUCER_RESULT_SET_HANDLER = new ResultSetHandler<Producer>() {
        @Override
        public Producer handle(ResultSet rs) throws SQLException {
            Producer p = new Producer();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setProductCount(rs.getInt("product_count"));
            return p;
        }

    };

    public final static ResultSetHandler<Integer> getCountResultSetHandler() {
        return new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
            }

        };
    }

    public final static <T> ResultSetHandler<T> getSingleResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {

        return new ResultSetHandler<T>() {
            @Override
            public T handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return oneRowResultSetHandler.handle(rs);
                } else {
                    return null;
                }

            }

        };
    }

    public final static <T> ResultSetHandler<List<T>> getListResultHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<List<T>>() {
            @Override
            public List<T> handle(ResultSet rs) throws SQLException {
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(oneRowResultSetHandler.handle(rs));
                }
                return list;
            }

        };
    }

}
