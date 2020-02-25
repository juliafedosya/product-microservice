package com.larecette.product.query.service;

import com.larecette.product.query.model.Product;
import com.larecette.product.query.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

}

