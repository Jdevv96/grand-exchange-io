package dev.jdevv.service;

import dev.jdevv.dao.ProductDao;
import dev.jdevv.dao.UserDao;
import dev.jdevv.dao.WishlistDao;
import dev.jdevv.model.User;
import dev.jdevv.model.Wishlist;
import dev.jdevv.model.WishlistItem;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

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

    public Wishlist getWishlistById(Principal principal, int wishlistId) {
        return wishlistDao.getWishlistById(wishlistId, getUser(principal).getId());
    }


    private User getUser(Principal principal) {
        String userName = principal.getName();
        return userDao.findByUsername(userName);
    }
}
