package dev.jdevv.controller;

import dev.jdevv.model.Wishlist;
import dev.jdevv.model.WishlistItem;
import dev.jdevv.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 *  The WishlistController is a class for handling HTTP requests related to
 *  creating, retrieving, adding and deleting a wishlist and it's items.
 *
 *  This class depends on an instance of WishlistService for interacting with DAOs handling necessary business logic.
 */

@RestController
@RequestMapping("/wishlists")
@PreAuthorize("isAuthenticated()")
public class WishlistController {

    WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public List<Wishlist> getUserWishlists(Principal principal) {
        return wishlistService.getUserWishlists(principal);
    }

    @GetMapping("/{wishlistId}")
    public Wishlist getWishlist(Principal principal, @PathVariable int wishlistId) {
        Wishlist wishlist = wishlistService.getWishlist(principal, wishlistId);
        if (wishlist == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return wishlist;
    }

    @PostMapping
    public Wishlist createWishlist(Principal principal, @Valid @RequestBody Wishlist wishlist) {
        return wishlistService.createWishlist(principal, wishlist);
    }

    @PostMapping("/{wishlistId}/products/{productId}")
    public void addWishlistItem(Principal principal, @PathVariable int wishlistId, @PathVariable int productId) {
        WishlistItem newItem = wishlistService.addWishlistItem(principal, wishlistId, productId);
        if (newItem == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID combination, please try again.");
        }
    }

    @DeleteMapping("/{wishlistId}/products/{productId}")
    public void removeWishlistItem(Principal principal, @PathVariable int wishlistId, @PathVariable int productId) {
        wishlistService.removeWishlistItem(principal, wishlistId, productId);
    }

    @DeleteMapping("/{wishlistId}")
    public void deleteWishlist(Principal principal, @PathVariable int wishlistId) {
        wishlistService.deleteWishlist(principal, wishlistId);
    }
}
