package com.larecette.product.query.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "larecette", type = "product")
public class ProductElastic {

    @NotNull
    private Double proteins;

    @NotNull
    private Double carbohydrates;

    @NotNull
    private Double fats;

    @NotNull
    private Integer calories;

    @NotNull
    private String name;

    @NotNull
    private String measureUnit;

    @Id
    private UUID id;

    @Override
    public String toString() {
        return "Product{" +
                "id: " + id + ", " +
                "name: " + name + ", " +
                "p1lat: " + fats + ", " +
                "p2lat: " + carbohydrates +
                "p3lat: " + proteins + ", " +
                "}";
    }

}

