package dev.jdevv.dao;

import dev.jdevv.model.CartItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCartItemDao implements CartItemDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCartItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CartItem getItemById(int cartItemId, int userId) {
        String sql = "SELECT * FROM cart_item WHERE cart_item_id = ? AND user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cartItemId, userId);
        if (results.next()) {
            CartItem item = mapRowToCartItem(results);
            return item;
        }
        return null;
    }

    @Override
    public CartItem getItemByProduct(int productId, int userId) {
        String sql = "SELECT * FROM cart_item WHERE product_id = ? AND user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId, userId);
        if (results.next()) {
            CartItem item = mapRowToCartItem(results);
            return item;
        }
        return null;
    }

    @Override
    public List<CartItem> getItemsForUser(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM cart_item WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            CartItem item = mapRowToCartItem(results);
            cartItems.add(item);
        }
        return cartItems;
    }

    @Override
    public int addCartItem(CartItem item) {
        return 0;
    }

    @Override
    public void updateCartItem(CartItem item) {

    }

    @Override
    public void removeCartItem(int cartItemId, int userId) {

    }

    @Override
    public void deleteCart(int userId) {

    }

    private CartItem mapRowToCartItem(SqlRowSet results) {
        CartItem item = new CartItem();
        item.setCartItemId(results.getInt("cart_item_id"));
        item.setUserId(results.getInt("user_id"));
        item.setProductId(results.getInt("product_id"));
        item.setQuantity(results.getInt("quantity"));
        return item;
    }
}
