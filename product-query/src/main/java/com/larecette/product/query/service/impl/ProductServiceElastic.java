package com.larecette.product.query.service.impl;
import com.larecette.product.query.model.ProductElastic;

import com.larecette.product.query.repository.ProductElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ProductServiceElastic {

    @Autowired
    private final ProductElasticRepository productElasticRepository;

    public ProductServiceElastic(ProductElasticRepository productElasticRepository) {
        this.productElasticRepository = productElasticRepository;
    }

    public ProductElastic findById(UUID id) {
        ProductElastic product = productElasticRepository.findById(id).orElse(null);
        return product;
    }

    public ProductElastic createProduct(ProductElastic product) {
        productElasticRepository.save(product);
        return product;
    }
}
