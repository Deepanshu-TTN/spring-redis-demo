package com.spring.redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @CachePut(value="products", key="#result.id")
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    @Cacheable(value = "products", key = "#id")
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @CacheEvict(value="products", key = "#id")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
