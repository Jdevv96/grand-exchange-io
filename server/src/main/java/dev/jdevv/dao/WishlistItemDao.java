package dev.jdevv.dao;

import dev.jdevv.model.Wishlist;
import dev.jdevv.model.WishlistItem;

import java.util.List;

public interface WishlistItemDao {
    List<WishlistItem> getWishlist();
}
