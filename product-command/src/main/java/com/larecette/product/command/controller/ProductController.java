package com.larecette.product.command.controller;

import com.larecette.product.command.dto.SaveProductDto;
import com.larecette.product.command.model.Product;
import com.larecette.product.command.repository.ProductRepository;
import com.larecette.product.command.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(HttpServletRequest request, @RequestBody SaveProductDto productDto) {

        Product product = productService.createProduct(productDto.productFromDto());

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Product> editProduct(HttpServletRequest request, @PathVariable(name = "id") Long id, @RequestBody SaveProductDto productDto) {
        Product product = productService.editProduct(productDto, id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteProduct(HttpServletRequest request, @PathVariable(name = "id") Long id) {
        Boolean existed = productService.delete(id);
        if (existed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
