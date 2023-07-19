package dev.jdevv.controller;

import dev.jdevv.dao.ProductDao;
import dev.jdevv.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String name, @RequestParam(required = false) String productSku) {
        if (name == null && productSku == null) {
            return productDao.getProducts();
        } else {
            return productDao.getProductsByNameAndSku(name, productSku);
        }
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable int productId) {
        Product product = productDao.getProduct(productId);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found. Please try again.");
        }
        return product;
    }
}
