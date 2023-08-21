package dev.jdevv.controller;

import dev.jdevv.model.Wishlist;
import dev.jdevv.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

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
}
