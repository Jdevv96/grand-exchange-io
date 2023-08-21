package dev.jdevv.service;

import dev.jdevv.dao.CartItemDao;
import dev.jdevv.dao.ProductDao;
import dev.jdevv.dao.UserDao;
import dev.jdevv.model.Cart;
import dev.jdevv.model.CartItem;
import dev.jdevv.model.Product;
import dev.jdevv.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.List;

@Component
public class CartService {

    private final CartItemDao cartItemDao;
    private final ProductDao productDao;
    private final UserDao userDao;
    private final TaxService taxService;

    public CartService(CartItemDao cartItemDao, ProductDao productDao, UserDao userDao, TaxService taxService) {
        this.cartItemDao = cartItemDao;
        this.productDao = productDao;
        this.userDao = userDao;
        this.taxService = taxService;
    }

    public Cart getUserCart(Principal principal) {
        User user = getUser(principal);
        int userId = getUser(principal).getId();
        List<CartItem> items = cartItemDao.getItemsForUser(userId);
        List<Product> products = productDao.getProductsInUserCart(userId);
        Cart cart = new Cart(items);

        for (CartItem each : items) {
            each.setProduct(findProduct(products, each.getProductId()));
        }

        BigDecimal taxRate = taxService.getTaxRate(user.getStateCode());
        BigDecimal subTotal = cart.getSubtotal();
        BigDecimal tax = subTotal.multiply(taxRate).setScale(2, RoundingMode.UP);
        cart.setTax(tax);

        return cart;
    }

    public CartItem addCartItem(Principal principal, CartItem item) {
        int userId = getUser(principal).getId();
        item.setUserId(userId);

        CartItem existingItem = cartItemDao.getItemByProduct(item.getProductId(), userId);

        if (existingItem == null) {
            int addedItemId = cartItemDao.addCartItem(item);
            return cartItemDao.getItemById(addedItemId, userId);
        } else {
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            cartItemDao.updateCartItem(existingItem);
            return cartItemDao.getItemById(existingItem.getCartItemId(), userId);
        }
    }

    public void removeCartItem(Principal principal, int cartItemId) {
        int userId = getUser(principal).getId();
        cartItemDao.removeCartItem(cartItemId, userId);
    }

    public void clearCartItems(Principal principal) {
        int userId = getUser(principal).getId();
        cartItemDao.deleteCart(userId);
    }

    /*
        ** Helper Methods **
     */

    private User getUser(Principal principal) {
        String userName = principal.getName();
        return userDao.findByUsername(userName);
    }

    private Product findProduct(List<Product> products, int productId) {
        for (Product product :  products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }
}
