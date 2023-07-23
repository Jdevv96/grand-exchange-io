package dev.jdevv.controller;

import dev.jdevv.model.Cart;
import dev.jdevv.model.CartItem;
import dev.jdevv.service.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/cart")
@PreAuthorize("isAuthenticated()")
public class CartController {

    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Cart getCart(Principal principal) {
        return cartService.getUserCart(principal);
    }

    @PostMapping("/items")
    public CartItem addCartItem(Principal principal, @Valid @RequestBody CartItem item) {
        return cartService.addCartItem(principal, item);
    }

    @DeleteMapping("/items/{cartItemId}")
    public void removeCartItem(Principal principal, @PathVariable int cartItemId) {
        cartService.removeCartItem(principal, cartItemId);
    }

    @DeleteMapping
    public void clearCart(Principal principal) {
        cartService.clearCartItems(principal);
    }
}
