/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.bntu.poisit.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import main.java.by.bntu.poisit.entity.Account;
import main.java.by.bntu.poisit.entity.Category;
import main.java.by.bntu.poisit.entity.Producer;
import main.java.by.bntu.poisit.entity.Product;
import main.java.by.bntu.poisit.exception.ServerErrorException;
import main.java.by.bntu.poisit.form.SearcherProductForm;
import main.java.by.bntu.poisit.jdbc.JDBCUtils;
import main.java.by.bntu.poisit.jdbc.ResultSetHandler;
import main.java.by.bntu.poisit.jdbc.ResultSetHandlerFactory;
import main.java.by.bntu.poisit.jdbc.SearchQuery;
import main.java.by.bntu.poisit.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private static final ResultSetHandler<List<Product>> productsResultSetHandler
            = ResultSetHandlerFactory.getListResultHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);

    private static final ResultSetHandler<Product> productResultSetHandler
            = ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);

    private static final ResultSetHandler<List<Category>> categoryListResultSetHandler
            = ResultSetHandlerFactory.getListResultHandler(ResultSetHandlerFactory.CATEGORY_RESULT_SET_HANDLER);

    private static final ResultSetHandler<List<Producer>> producerListResultSetHandler
            = ResultSetHandlerFactory.getListResultHandler(ResultSetHandlerFactory.PRODUCER_RESULT_SET_HANDLER);

    private final ResultSetHandler<Integer> countResultSetHandler = ResultSetHandlerFactory.getCountResultSetHandler();

    private final DataSource dataSource;

    public ProductServiceImpl(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> listAllProducts(int page, int limit) {
        try ( Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(c, "SELECT p.*, c.name as category, pr.name as producer FROM product p, producer"
                    + " pr, category c WHERE c.id = p.id_category AND pr.id = p.id_producer limit ? offset ?",
                    productsResultSetHandler, limit, offset);
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }

    }


    @Override
    public void addProductToSystem(Product pr, Integer idCategory, Integer idProducer) {
        try ( Connection c = dataSource.getConnection()) {

            String insert = "INSERT INTO product values (? ,? , ? , ?, ?, ?, ?)";

            PreparedStatement prIns = c.prepareStatement(insert);
            prIns.setInt(1, pr.getId());
            prIns.setString(2, pr.getName());
            prIns.setString(3, pr.getDescription());
            prIns.setString(4, pr.getImageLink());
            prIns.setBigDecimal(5, pr.getPrice());
            prIns.setInt(6, idCategory);
            prIns.setInt(7, idProducer);
            prIns.executeUpdate();

        } catch (SQLException ex) {
            throw new ServerErrorException("Can't execute SQL request: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteProductFromSystem(int idPr) {
        try ( Connection c = dataSource.getConnection()) {
            String sql = "DELETE FROM product WHERE id = ?  ";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, idPr);
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new ServerErrorException("Can't execute SQL request: " + ex.getMessage(), ex);
        }
    }

    public int selectIdFromNameOfCategory(String nameCategory) {
        try ( Connection c = dataSource.getConnection()) {
            int id = 0;
            String sql = "SELECT id FROM category WHERE name = ?";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, nameCategory);
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
    public int findIdFromProduct(Integer idProduct) {
        try ( Connection c = dataSource.getConnection()) {
            int id = 0;
            String sql = "SELECT id FROM product WHERE id = ?";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, idProduct);
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
    public int findIdFromProductForCheckEmpty(Integer idProduct) {
        try ( Connection c = dataSource.getConnection()) {
            int id = 0;
            String sql = "SELECT id FROM product WHERE id = ?";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, idProduct);
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
    public String findNameFromProductForCheckEmpty(String nameProduct) {
        try ( Connection c = dataSource.getConnection()) {
            String name = null;
            String sql = "SELECT name FROM product WHERE name = ?";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, nameProduct);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                name = res.getString("name");
            }

            return name;

        } catch (SQLException ex) {
            throw new ServerErrorException("Can't execute SQL request: " + ex.getMessage(), ex);

        }

    }

    @Override
    public int selectIdFromNameOfProducer(String nameProducer) {
        try ( Connection c = dataSource.getConnection()) {
            int id = 0;
            String sql = "SELECT id FROM producer WHERE name = ?";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, nameProducer);
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
    public List<Product> listProductsByCategory(String categoryUrl, int page, int limit) {
        try ( Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(c, "SELECT p.*, c.name as category, pr.name as producer FROM product p, producer"
                    + " pr, category c WHERE c.url = ? AND pr.id = p.id_producer AND c.id = id_category order by p.id limit ? offset ?",
                    productsResultSetHandler, categoryUrl, limit, offset);
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Category> listAllCategories() {
        try ( Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "SELECT * FROM category order by id", categoryListResultSetHandler);

        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Producer> listAllProducers() {
        try ( Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "SELECT * FROM producer order by name", producerListResultSetHandler);

        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }

    @Override
    public int countAllProducts() {
        try ( Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "SELECT count(*) FROM product",
                    countResultSetHandler);
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }

    }

    @Override
    public int countProductsByCategory(String categoryUrl) {
        try ( Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "SELECT count(p.*) FROM product p, category c "
                    + " WHERE c.id = p.id_category and c.url=?", countResultSetHandler, categoryUrl);

        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> listProductsBySearcherProductForm(SearcherProductForm sPF, int page, int limit) {
        try ( Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            SearchQuery sq = buildSearchQuery("p.*, c.name as category, pr.name as producer", sPF);
            sq.getSql().append(" order by p.id limit ? offset ?");
            sq.getParams().add(limit);
            sq.getParams().add(offset);
            LOGGER.debug("search query={} with params={}", sq.getSql(), sq.getParams());
            return JDBCUtils.select(c, sq.getSql().toString(), productsResultSetHandler, sq.getParams().toArray());
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }

    protected SearchQuery buildSearchQuery(String selectFields, SearcherProductForm sPF) {
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select ");
        sql.append(selectFields).append(" from product p, category c, producer pr where pr.id=p.id_producer and c.id=p.id_category and (p.name ilike ? or p.description ilike ?)");
        params.add("%" + sPF.getQuery() + "%");
        params.add("%" + sPF.getQuery() + "%");
        JDBCUtils.populateSqlAndParams(sql, params, sPF.getCategories(), "c.id = ?");
        JDBCUtils.populateSqlAndParams(sql, params, sPF.getProducers(), "pr.id = ?");
        return new SearchQuery(sql, params);
    }

    @Override
    public int countProductsBySearcherProductForm(SearcherProductForm sPF) {
        try ( Connection c = dataSource.getConnection()) {
            SearchQuery sq = buildSearchQuery("count(*)", sPF);
            LOGGER.debug("search query={} with params={}", sq.getSql(), sq.getParams());
            return JDBCUtils.select(c, sq.getSql().toString(), countResultSetHandler, sq.getParams().toArray());
        } catch (SQLException e) {
            throw new ServerErrorException("Can't execute SQL request: " + e.getMessage(), e);
        }
    }

}
