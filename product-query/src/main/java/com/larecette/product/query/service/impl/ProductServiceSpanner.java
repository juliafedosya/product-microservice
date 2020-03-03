package com.larecette.product.query.service.impl;

import com.larecette.product.query.model.ProductSpanner;
import com.larecette.product.query.repository.ProductSpannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ProductServiceSpanner {

    @Autowired
    private final ProductSpannerRepository productSpannerRepository;

    public ProductServiceSpanner(ProductSpannerRepository productSpannerRepository) {
        this.productSpannerRepository = productSpannerRepository;
    }

    public ProductSpanner findById(UUID id) {
        ProductSpanner productSpanner = productSpannerRepository.findById(id).orElse(null);
        return productSpanner;
    }
}
