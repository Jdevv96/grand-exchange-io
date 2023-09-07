package dev.jdevv.dao;

import dev.jdevv.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class JdbcProductDaoTests extends BaseDaoTests {
    protected static final Product EXPECTED_PRODUCT_1 = new Product(1, "SKU-001",
            "Product 1", "Product description 1.", new BigDecimal("1.99"), "Product001.jpg");
    protected static final Product EXPECTED_PRODUCT_2 = new Product(2, "SKU-002",
            "Product 2", "Product description 2.", new BigDecimal("2.99"), "Product002.jpg");
    protected static final Product EXPECTED_PRODUCT_3 = new Product(3, "SKU-003",
            "Product 3", "Product description 3.", new BigDecimal("3.99"), "Product003.jpg");

    private static final int NUMBER_OF_PRODUCTS = 7;
    private static final int INVALID_PRODUCT_ID = 9;

    private JdbcProductDao dao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcProductDao(jdbcTemplate);
    }

    @Test
    public void getProduct_with_valid_id_returns_valid_product() {
        Product testProduct = dao.getProduct(EXPECTED_PRODUCT_1.getProductId());
        Assert.assertEquals("getProduct ID: " + EXPECTED_PRODUCT_1.getProductId() + ", returned the wrong product.", EXPECTED_PRODUCT_1, testProduct);
    }

    @Test
    public void getProduct_with_invalid_product_returns_null() {
        Product invalidProduct = dao.getProduct(INVALID_PRODUCT_ID);
        Assert.assertNull("getProduct failed to return null for Product id not found in database.", invalidProduct);

        Product nullProduct = null;
        Assert.assertNull("getProduct failed to return null for a null Product.", nullProduct);
    }

    @Test
    public void getProducts_returns_all_products() {
        List<Product> matchingProducts = dao.getProducts();
        Assert.assertNotNull(matchingProducts);

        int actualNumberOfProducts = matchingProducts.size();
        Assert.assertEquals(NUMBER_OF_PRODUCTS, actualNumberOfProducts);
    }

    @Test
    public void searchProducts_null_name_and_sku_returns_all_products() {
        List<Product> matchingProducts = dao.searchProducts(null, null);
        Assert.assertNotNull(matchingProducts); // List should by default, retrieve all products.

        int actualNumberOfProducts = matchingProducts.size();
        Assert.assertEquals(NUMBER_OF_PRODUCTS, actualNumberOfProducts); // Ensure expected list and actual list and have the same amount of products.

        Product actualProduct1 =  matchingProducts.get(0);
        Assert.assertEquals(EXPECTED_PRODUCT_1, actualProduct1); // Ensure the first item from the actual list matches the first control product.
    }

    @Test
    public void searchProducts_blank_sku_and_null_name_returns_all_products() {
        List<Product> matchingProducts = dao.searchProducts(null, "");
        Assert.assertNotNull(matchingProducts);

        int actualNumberOfProducts = matchingProducts.size();
        Assert.assertEquals(NUMBER_OF_PRODUCTS, actualNumberOfProducts);
    }

    @Test
    public void searchProducts_blank_name_and_null_sku_returns_all_products() {
        List<Product> matchingProducts = dao.searchProducts("", null);
        Assert.assertNotNull(matchingProducts);

        int actualNumberOfProducts = matchingProducts.size();
        Assert.assertEquals(NUMBER_OF_PRODUCTS, actualNumberOfProducts);
    }

    @Test
    public void searchProducts_with_no_matches_returns_empty() {
        List<Product> matchingProducts = dao.searchProducts("Item 1", "SKU_ITEM_1");
        Assert.assertNotNull(matchingProducts);

        int numberOfMatches = matchingProducts.size();
        Assert.assertEquals(0, numberOfMatches);
    }

    @Test
    public void searchProducts_with_partial_sku_returns_empty() {
        List<Product> matchingProducts = dao.searchProducts(null, "SKU");
        Assert.assertNotNull(matchingProducts);

        int numberOfMatches = matchingProducts.size();
        Assert.assertEquals(0, numberOfMatches);
    }

    @Test
    public void searchProducts_with_exact_sku_returns_matching_product() {
        List<Product> matchingProducts = dao.searchProducts(null, "SKU-002");
        Assert.assertNotNull(matchingProducts);
        Assert.assertEquals(EXPECTED_PRODUCT_2, matchingProducts.get(0));
        Assert.assertEquals(1, matchingProducts.size());
    }

    @Test
    public void searchProducts_with_partial_name_returns_matching_products() {
        List<Product> matchingProducts = dao.searchProducts("oduc", null);
        Assert.assertNotNull(matchingProducts);
        Assert.assertEquals(EXPECTED_PRODUCT_2, matchingProducts.get(1));
        Assert.assertEquals(EXPECTED_PRODUCT_3, matchingProducts.get(2));

        int actualNumberOfProducts = matchingProducts.size();
        Assert.assertEquals(NUMBER_OF_PRODUCTS, actualNumberOfProducts);
    }

    @Test
    public void searchProducts_with_exact_name_returns_matching_product() {
        List<Product> matchingProducts = dao.searchProducts("Product 1", null);
        Assert.assertNotNull(matchingProducts);
        Assert.assertEquals(EXPECTED_PRODUCT_1, matchingProducts.get(0));
        Assert.assertEquals(1, matchingProducts.size());
    }

    @Test
    public void searchProducts_with_partial_name_and_exact_sku_returns_matching_product() {
        List<Product> matchingProducts = dao.searchProducts("oduc", "SKU-003");
        Assert.assertNotNull(matchingProducts);
        Assert.assertEquals(EXPECTED_PRODUCT_3, matchingProducts.get(0));
        Assert.assertEquals(1, matchingProducts.size());
    }

    @Test
    public void searchProducts_with_invalid_name_and_exact_sku_returns_empty_list() {
        List<Product> matchingProducts = dao.searchProducts("Mug", "SKU-003");
        Assert.assertNotNull(matchingProducts);
        Assert.assertEquals(0, matchingProducts.size());
    }
}
