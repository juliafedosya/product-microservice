package com.larecette.product.command.service;

import com.larecette.product.command.dto.SaveProductDto;
import com.larecette.product.command.model.Product;
import com.larecette.product.command.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public Product editProduct(SaveProductDto productDto, UUID id) {

        Product oldProduct = productRepository.findById(id).orElse(null);
        if(oldProduct == null){
            return null;
        }

        oldProduct.setName(productDto.getName());
        oldProduct.setMeasureUnit(productDto.getMeasureUnit());
        oldProduct.setCarbohydrates(productDto.getCarbohydrates());
        oldProduct.setFats(productDto.getFats());
        oldProduct.setProteins(oldProduct.getProteins());
        oldProduct.setCalories(oldProduct.calculateCalories());
        productRepository.save(oldProduct);
        return oldProduct;
    }

    public Boolean delete(UUID id){
        Boolean exists = productRepository.existsById(id);
        if(exists) {
            productRepository.deleteById(id);
        }
        return exists;
    }

}

