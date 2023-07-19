package dev.jdevv.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    private BigDecimal getSubtotal() {
        BigDecimal subtotal = new BigDecimal("0.00");
        for (CartItem each : items) {
            subtotal = subtotal.add((each.getProduct().getPrice().multiply(new BigDecimal(each.getQuantity()))));
        }
        return subtotal;
    }

    private BigDecimal getTotal() {
        return getSubtotal().add(getTax());
    }
}
