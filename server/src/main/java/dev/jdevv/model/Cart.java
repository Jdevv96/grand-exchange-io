package dev.jdevv.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO class representing a Cart.
 *      - A cart consists of a list of products (cartItems).
 *      - The tax rate, varying by the users state. (This logic is handled via the TaxService class.)
 */

public class Cart {

    private List<CartItem> items;
    private BigDecimal tax;

    public Cart() {
        this.items = new ArrayList<>();
        this.tax = new BigDecimal("0.00");
    }

    public Cart(List<CartItem> items) {
        this();
        this.items = items;
    }

    public CartItem[] getItems() {
        CartItem[] cartItems = new CartItem[items.size()];
        return items.toArray(cartItems);
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     *  Note: The derived methods below are used to calculate the subtotal based on the items in the users cart,
     *  or $0.00 if the cart is empty.
     *
     *  Followed by calculating the total.
     */

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = new BigDecimal("0.00");
        subtotal.setScale(2);
        for (CartItem each : items) {
            subtotal = subtotal.add(each.getProduct().getPrice().multiply(new BigDecimal(each.getQuantity())));
        }
        return subtotal;
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(tax);
    }
}
