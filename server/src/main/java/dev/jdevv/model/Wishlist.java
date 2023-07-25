package dev.jdevv.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {

    private int wishlistId;
    private int userId;
    private String name;

    private List<WishlistItem> wishlistItemList;

    public Wishlist() {
        this.wishlistItemList = new ArrayList<>();
    }

    public Wishlist(List<WishlistItem> wishlistItemList) {
        this();
        this.wishlistItemList = wishlistItemList;
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

    public WishlistItem[] getWishlistItemList() {
        WishlistItem[] wishlistItems = new WishlistItem[wishlistItemList.size()];
        return wishlistItemList.toArray(wishlistItems);
    }
}
