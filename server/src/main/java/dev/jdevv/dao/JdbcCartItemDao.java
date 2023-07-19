package dev.jdevv.dao;

import dev.jdevv.model.Cart;
import dev.jdevv.model.CartItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
public class JdbcCartItemDao implements CartItemDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCartItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public CartItem getItemById(int cartItemId, int userId) {
        return null;
    }

    @Override
    public CartItem getItemByProduct(int productId, int userId) {
        return null;
    }

    @Override
    public List<CartItem> getItemsForUser(int userId) {
        return null;
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
}
