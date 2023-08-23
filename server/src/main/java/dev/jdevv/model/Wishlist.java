package dev.jdevv.model;

import java.util.List;

/**
 * POJO class representing a wishlist.
 *      - A wishlist is associated with a list of wishlistItems.
 */

public class Wishlist {

    private int wishlistId;
    private int userId;
    private String name;

    private List<WishlistItem> items;

    public Wishlist() {
    }

    public Wishlist(int wishlistId, int userId, String name) {
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.name = name;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WishlistItem> getItems() {
        return items;
    }

    public void setItems(List<WishlistItem> items) {
        this.items = items;
    }
}
