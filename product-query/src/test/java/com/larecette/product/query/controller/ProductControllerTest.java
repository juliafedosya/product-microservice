package com.larecette.product.query.controller;

import com.larecette.product.query.model.Product;
import com.larecette.product.query.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class ProductControllerTest {

    public static final String PRODUCT_NAME = "chesnok";
    public static final String MEASURE_UNIT = "zubchik";
    public static final UUID RANDOM_ID = UUID.randomUUID();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void shouldReturnProductAnd200() throws Exception {
        Product product = new Product(2.0,0.0,0.0,8,PRODUCT_NAME,MEASURE_UNIT, RANDOM_ID);
        Mockito.when(productService.findById(product.getId()))
                .thenReturn(product);
        this.mockMvc.perform(get("/product/" + product.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(PRODUCT_NAME))).andExpect(content().string(containsString(MEASURE_UNIT)));
    }

    @Test
    void shouldReturn404() throws Exception {
        Product product = new Product(2.0,0.0,0.0,8,PRODUCT_NAME,MEASURE_UNIT, RANDOM_ID);
        Mockito.when(productService.findById(product.getId()))
                .thenReturn(null);
        this.mockMvc.perform(get("/product/" + product.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}