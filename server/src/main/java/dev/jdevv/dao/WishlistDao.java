package dev.jdevv.dao;

import dev.jdevv.model.Wishlist;
import dev.jdevv.model.WishlistItem;

import java.util.List;

public interface WishlistDao {

    /**
     * Retrieve a particular wishlist given a users id and a wishlist id.
     *
     * @param wishlistId ID of the wishlist to search for.
     * @param userId ID of the user to search.
     * @return The wishlist matching the id or null if not found.
     */
    Wishlist getWishlistById(int wishlistId, int userId);

    /**
     * Retrieve all wishlists for a particular user.
     *
     * @param userId The userid used to get wishlists.
     * @return List of all wishlists for a given user.
     */
    List<Wishlist> getWishlists(int userId);

    /**
     * Add a product to a wishlist as an item.
     *
     * @param wishlistItem The item to add to the wishlist.
     * @return The id of the newly added wishlistItem.
     */
    int addWishlistItem(WishlistItem wishlistItem);

    /**
     * Remove a wishlistItem from a given wishlist.
     *
     * @param wishlistId The id of the wishlist to update.
     * @param userId The id of the user who's wishlist will be updated.
     * @param productId The product item to remove.
     */
    void removeWishlistItem(int wishlistId, int userId, int productId);

    /**
     * Add a new wishlist to the users list.
     *
     * @param wishlist The wishlist to add.
     * @return The newly added wishlist.
     */
    Wishlist createWishlist(Wishlist wishlist);

    /**
     * Removes a wishlist for a given user.
     * @param wishlistId The id of the wishlist to remove.
     * @param userId The id of the user who's wishlist will be removed.
     */
    void removeWishlist(int wishlistId, int userId);


}
