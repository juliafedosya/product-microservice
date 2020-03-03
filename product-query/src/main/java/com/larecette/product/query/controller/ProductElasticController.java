package com.larecette.product.query.controller;

import com.larecette.product.query.model.ProductElastic;
import com.larecette.product.query.service.impl.ProductServiceElastic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping(value = "/product")
@Slf4j
public class ProductElasticController {

    @Autowired
    private ProductServiceElastic productServiceElastic;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductElastic> getProductById(@PathVariable UUID id) {
        log.info("in GET mapping {}",id);
        ProductElastic productElastic = productServiceElastic.findById(id);
        if(productElastic == null){
            log.info("in GET mapping, product not found by id {}",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productElastic,HttpStatus.OK);
    }

}
