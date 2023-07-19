package dev.jdevv.dao;

import dev.jdevv.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    List<Product> getProductsByNameAndSku(String name, String productSku);

    Product getProduct(int productId);


}
