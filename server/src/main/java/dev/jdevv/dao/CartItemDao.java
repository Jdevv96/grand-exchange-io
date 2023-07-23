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
     * Add a cartItem to the cartItem database, based on a given cartItem.
     *
     * @param item The given cartItem to add.
     * @return The new cartItemId of the added item.
     */
    int addCartItem(CartItem item);

    /**
     * Update the quantity for a cartItem, based on a given cartItem.
     *
     * @param item The existing cartItem and quantity to add.
     */
    void updateCartItem(CartItem item);

    /**
     * Removes a cartItem from the database, regardless of quantity, based on a given cartItemId and a user.
     *
     * @param cartItemId The id of the cartItem to remove.
     * @param userId The id of the users cart to update.
     */
    void removeCartItem(int cartItemId, int userId);

    /**
     * Removes all items from a given users cart, regardless of quantity.
     *
     * @param userId The id of the user to remove items from.
     */
    void deleteCart(int userId);
}
