package dev.jdevv.service;

import dev.jdevv.dao.ProductDao;
import dev.jdevv.dao.UserDao;
import dev.jdevv.dao.WishlistDao;
import dev.jdevv.model.Product;
import dev.jdevv.model.User;
import dev.jdevv.model.Wishlist;
import dev.jdevv.model.WishlistItem;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

/**
 * The WishlistService is a class for handling the business logic around creating, retrieving,
 * updating and deleting wishlists and wishlistItems.
 *
 * This depends on three DAOs for retrieving and storing data: UserDao, ProductDao and WishlistDao.
 */

@Component
public class WishlistService {

    private final UserDao userDao;
    private final ProductDao productDao;
    private final WishlistDao wishlistDao;

    public WishlistService(UserDao userDao, ProductDao productDao, WishlistDao wishlistDao) {
        this.userDao = userDao;
        this.productDao = productDao;
        this.wishlistDao = wishlistDao;
    }

    public List<Wishlist> getUserWishlists(Principal principal) {
        int userId = getUser(principal).getId();
        return wishlistDao.getWishlists(userId);
    }

    public Wishlist getWishlist(Principal principal, int wishlistId) {
        return getWishlistById(getUser(principal).getId(), wishlistId);
    }

    public Wishlist getWishlistById(int userId, int wishlistId) {
        Wishlist wishlist = wishlistDao.getWishlistById(userId, wishlistId);
        if (wishlist == null) {
            return null;
        }
        return getProductDetails(wishlist);
    }

    public WishlistItem addWishlistItem(Principal principal, int wishlistId, int productId) {
        int userId = getUser(principal).getId();
        Wishlist wishlist = wishlistDao.getWishlistById(wishlistId, userId);
        if (wishlist == null) {
            return null;
        }

        if (productDao.getProduct(productId) == null) {
            return null;
        }

        WishlistItem existingItem = wishlistDao.getWishlistItemByProduct(wishlistId, productId);
        if (existingItem != null) {
            return existingItem;
        }
        WishlistItem newItem = new WishlistItem();
        newItem.setWishlistId(wishlistId);
        newItem.setProductId(productId);
        newItem.setWishlistItemId(wishlistDao.addWishlistItem(newItem));
        return newItem;
    }

    public void removeWishlistItem(Principal principal, int wishlistId, int productId) {
        int userId = getUser(principal).getId();
        wishlistDao.removeWishlistItem(wishlistId, userId, productId);
    }

    public Wishlist createWishlist(Principal principal, Wishlist wishlist) {
        int userId = getUser(principal).getId();
        wishlist.setUserId(userId);
        Wishlist newList = wishlistDao.createWishlist(wishlist);
        return getProductDetails(newList);
    }

    public void deleteWishlist(Principal principal, int wishlistId) {
        int userId = getUser(principal).getId();
        wishlistDao.removeWishlist(wishlistId, userId);
    }

    /*
        ** Helper Methods **
     */

    private User getUser(Principal principal) {
        String userName = principal.getName();
        return userDao.findByUsername(userName);
    }

    private Wishlist getProductDetails(Wishlist wishlist) {
        List<Product> products = productDao.getProductsInWishlist(wishlist.getWishlistId());

        for (WishlistItem item : wishlist.getItems()) {
            item.setProduct(findProduct(products, item.getProductId()));
        }
        return wishlist;
    }

    private Product findProduct(List<Product> products, int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }
}
