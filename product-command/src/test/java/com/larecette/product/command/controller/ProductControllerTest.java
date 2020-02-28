package com.larecette.product.command.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.larecette.product.command.dto.SaveProductDto;
import com.larecette.product.command.model.Product;
import com.larecette.product.command.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    public static final String MAIN_ENDPOINT = "/product/";
    public static final String PRODUCT_NAME = "chesnok";
    public static final String MEASURE_UNIT = "zubchik";
    public static final UUID RANDOM_ID = UUID.randomUUID();


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void shouldCreateProductAndReturn201() throws Exception {
        this.mockMvc.perform(post(MAIN_ENDPOINT )
                .content(asJsonString(new SaveProductDto(PRODUCT_NAME,2.1,3.0,4.0,MEASURE_UNIT)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateProductAndReturn200() throws Exception {
        SaveProductDto productDto = new SaveProductDto(PRODUCT_NAME,2.1,3.0,4.0,MEASURE_UNIT);
        Product returnedProduct = productDto.productFromDto();
        returnedProduct.setCalories(returnedProduct.calculateCalories());
        returnedProduct.setId(RANDOM_ID);

        Mockito.when(productService.editProduct(productDto, RANDOM_ID))
                .thenReturn(returnedProduct);

        this.mockMvc.perform(patch(MAIN_ENDPOINT + RANDOM_ID )
                .content(asJsonString(productDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(PRODUCT_NAME)))
                .andExpect(content().string(containsString(MEASURE_UNIT)))
                .andExpect(content().string(containsString(RANDOM_ID.toString())));
    }

    @Test
    public void shouldReturn404OnUpdateIfResourceNotFound() throws Exception {
        SaveProductDto productDto = new SaveProductDto(PRODUCT_NAME,2.1,3.0,4.0,MEASURE_UNIT);

        Mockito.when(productService.editProduct(productDto, RANDOM_ID))
                .thenReturn(null);

        this.mockMvc.perform(patch(MAIN_ENDPOINT + RANDOM_ID )
                .content(asJsonString(productDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteProductAndReturn204() throws Exception {
        Mockito.when(productService.delete(RANDOM_ID))
                .thenReturn(true);
        this.mockMvc.perform(delete(MAIN_ENDPOINT + RANDOM_ID )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn404OnDeleteIfResourceNotFound() throws Exception {
        Mockito.when(productService.delete(RANDOM_ID))
                .thenReturn(false);
        this.mockMvc.perform(delete(MAIN_ENDPOINT + RANDOM_ID )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}