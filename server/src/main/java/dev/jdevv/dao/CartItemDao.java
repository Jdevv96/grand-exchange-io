package dev.jdevv.dao;

import dev.jdevv.model.CartItem;

import java.util.List;

public interface CartItemDao {

    /**
     * Get a cartItem from the database, based on a user and the carItem's id.
     *
     * @param cartItemId The id of the cartItem to return.
     * @param userId The id of a specified user.
     * @return The matching cartItem or null if not found.
     */
    CartItem getItemById(int cartItemId, int userId);

    /**
     * Get a cartItem from the database, based on a user and a product's id.
     *
     * @param productId The id of the product to return.
     * @param userId The id of a specified user.
     * @return The matching cartItem or null if not found.
     */
    CartItem getItemByProduct(int productId, int userId);

    /**
     * Get a list of cartItems from the database, based on a specific user id.
     *
     * @param userId The id of the user to retrieve cartItems from.
     * @return List of all cartItems for a particular user, or an empty cart if no products have been added.
     */
    List<CartItem> getItemsForUser(int userId);

    /**
     *
     *
     * @param item
     * @return
     */
    int addCartItem(CartItem item);

    /**
     *
     *
     * @param item
     */
    void updateCartItem(CartItem item);

    /**
     *
     *
     * @param cartItemId
     * @param userId
     */
    void removeCartItem(int cartItemId, int userId);

    /**
     *
     *
     * @param userId
     */
    void deleteCart(int userId);
}
