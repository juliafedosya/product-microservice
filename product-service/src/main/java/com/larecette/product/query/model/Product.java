package com.larecette.product.query.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Table(name = "product",schema="larecette")
@Data
@Entity
public class Product {

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
    @Column(name = "measure_unit")
    private String measureUnit;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

