package dev.jdevv.dao;

import dev.jdevv.model.Product;
import java.util.List;

/**
 * The ProductDao interface is used to interact with the product information in the data store.
 */

public interface ProductDao {

    /**
     * Get a list of products from the database, ordered numerically by the product id.
     *
     * @return List of all Product objects.
     */
    List<Product> getProducts();

    /**
     * Get a list of products from the database, filtered by either a product's name, the sku or both.
     * Products are ordered numerically by the product id.
     *
     * @param name The product name to search by (can be partial match, case-insensitive). *Optional
     * @param productSku The exact product sku to search by. *Optional
     * @return List of all products with a name and/or sku matching or all products if no matches or no search queries.
     */
    List<Product> getProductsByNameAndSku(String name, String productSku);

    /**
     * Get a list of products from the database, based on the logged-in user's id.
     * Used when building user cart, for retrieving product information.
     * Products are ordered numerically by the product id.
     *
     * @param userId The users user id to filter products by.
     * @return List of all products in a users cart or an empty cart if no products found.
     */
    List<Product> getProductsInUserCart(int userId);

    /**
     * Retrieves the products and their info in a wishlist given the id.
     *
     * @param wishlistId The products will be based on this wishlist id.
     * @return List of Products in wishlist.
     */
    List<Product> getProductsInWishlist(int wishlistId);

    /**
     * Get a product from the datastore, based on the product id.
     *
     * @param productId The product id to search by.
     * @return Product object matching the id, or null if not found.
     */
    Product getProduct(int productId);



}
