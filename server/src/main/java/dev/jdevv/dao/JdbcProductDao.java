package dev.jdevv.dao;

import dev.jdevv.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *  The JdbcProductDao class is the concrete implementation of the ProductDao interface,
 *  that allows us to interact with the product information.
 *
 *  This class is specifically used to access data from a SQL database.
 *  This DAO class supports Reading for products.
 */

@Component
public class JdbcProductDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product getProduct(int productId) {
        Product product = new Product();
        String sql = "SELECT * FROM product WHERE product_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        if (results.next()) {
            product = mapRowToProduct(results);
            return product;
        }
        return null;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY product_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> searchProducts(String name, String productSku) {
        List<Product> products = new ArrayList<>();
        String sqlName = "%" + (name == null ? "" : name) + "%";
        boolean validSku = productSku != null && productSku.trim().length() > 0;


        String sql = "SELECT * FROM product WHERE name ILIKE ? " + (validSku ? " AND product_sku = ? " : "") + " ORDER BY product_id;";
        SqlRowSet results;
        if (validSku) {
            results = jdbcTemplate.queryForRowSet(sql, sqlName, productSku);
        } else {
            results = jdbcTemplate.queryForRowSet(sql, sqlName);
        }

        while (results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getProductsInUserCart(int userId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product " +
                "WHERE product_id IN (" +
                "SELECT product_id FROM cart_item " +
                "WHERE user_id = ?" +
                ") ORDER BY product_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getProductsInWishlist(int wishlistId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_id IN (SELECT product_id FROM wishlist_item WHERE wishlist_id = ?);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, wishlistId);
        while (results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    private Product mapRowToProduct(SqlRowSet results) {
        Product product = new Product();
        product.setProductId(results.getInt("product_id"));
        product.setProductSku(results.getString("product_sku"));
        product.setName(results.getString("name"));
        product.setPrice(results.getBigDecimal("price"));
        product.setDescription(results.getString("description"));
        product.setImageName(results.getString("image_name"));

        return product;
    }
}
