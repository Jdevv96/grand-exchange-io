package dev.jdevv.model;

import java.math.BigDecimal;

/**
 * POJO class representing a tax rate. TaxRate is dependent upon the users registered state.
 */

public class TaxRate {

    private BigDecimal salesTax;

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }
}
