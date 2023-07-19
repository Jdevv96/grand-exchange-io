package dev.jdevv.dao;

import dev.jdevv.model.Cart;
import dev.jdevv.model.CartItem;

import java.security.Principal;
import java.util.List;

public interface CartItemDao {

    CartItem getItemById(int cartItemId, int userId);
    CartItem getItemByProduct(int productId, int userId);
    List<CartItem> getItemsForUser(int userId);
    int addCartItem(CartItem item);
    void updateCartItem(CartItem item);
    void removeCartItem(int cartItemId, int userId);
    void deleteCart(int userId);
}
