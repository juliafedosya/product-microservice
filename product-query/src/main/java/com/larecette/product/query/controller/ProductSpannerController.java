package com.larecette.product.query.controller;

import com.larecette.product.query.model.ProductSpanner;
import com.larecette.product.query.service.impl.ProductServiceSpanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/product/consistency")
@Slf4j
public class ProductSpannerController {

    @Autowired
    private ProductServiceSpanner productServiceSpanner;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductSpanner> getProductById(@PathVariable UUID id) {
        log.info("in GET mapping consistency {}",id);
        ProductSpanner productSpanner = productServiceSpanner.findById(id);
        if(productSpanner == null){
            log.info("in GET mapping consistency, product not found by id {}",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productSpanner,HttpStatus.OK);
    }



}
