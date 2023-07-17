package dev.jdevv.dao;

import dev.jdevv.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    Product getProduct(int productId);


}
