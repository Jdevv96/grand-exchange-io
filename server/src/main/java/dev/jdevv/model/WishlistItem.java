package dev.jdevv.model;

/**
 * POJO class representing a WishlistItem.
 *      - WishlistItems are associated to a users wishlist.
 */

public class WishlistItem {

    private int wishlistItemId;
    private int wishlistId;
    private int productId;

    private Product product;

    public WishlistItem() {
    }

    public WishlistItem(int wishlistItemId, int wishlistId, int productId) {
        this.wishlistItemId = wishlistItemId;
        this.wishlistId = wishlistId;
        this.productId = productId;
    }

    public int getWishlistItemId() {
        return wishlistItemId;
    }

    public void setWishlistItemId(int wishlistItemId) {
        this.wishlistItemId = wishlistItemId;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
