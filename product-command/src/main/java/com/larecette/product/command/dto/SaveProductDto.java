package com.larecette.product.command.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.larecette.product.command.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class SaveProductDto {

    private String name;

    private Double proteins;

    private Double carbohydrates;

    private Double fats;

    private String measureUnit;

    private Integer calculateCalories(){
        Integer calories = (int)(fats*9 + carbohydrates *4 + proteins*4);
        return calories;
    }

    public Product productFromDto(){
        Product product = new Product();
        product.setName(name);
        product.setProteins(proteins);
        product.setCarbohydrates(carbohydrates);
        product.setFats(fats);
        product.setCalories(calculateCalories());
        product.setMeasureUnit(measureUnit);
        return product;
    }
}
