package dev.jdevv.server;

import dev.jdevv.dao.JdbcProductDao;
import dev.jdevv.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class JdbcProductDaoTests extends BaseDaoTests {
    protected static final Product PRODUCT_1 = new Product(1, "SKU-001", "Product 1", "Product description 1.", new BigDecimal("1.99"), "Product001.jpg");
    protected static final Product PRODUCT_2 = new Product(2, "SKU-002", "Product 2", "Product description 2.", new BigDecimal("2.99"), "Product002.jpg");
    protected static final Product PRODUCT_3 = new Product(3, "SKU-003", "Product 3", "Product description 3.", new BigDecimal("3.99"), "Product003.jpg");

    private JdbcProductDao productDao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        productDao = new JdbcProductDao(jdbcTemplate);
    }

    @Test
    public void getAll_returns_all_products() {
        List<Product> products = productDao.getProducts();

        Assert.assertNotNull(products);
        Assert.assertEquals(7, products.size());
        Assert.assertEquals(PRODUCT_2, products.get(1));
    }





}
