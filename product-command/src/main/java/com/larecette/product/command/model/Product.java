package com.larecette.product.command.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table(name = "products")
@Data
@Entity
public class Product {

    //EG stands for energy density, kcal per gram
    private static final double FAT_EG = 9.29;
    private static final double CARBOHYDRATES_EG = 4.1;
    private static final double PROTEINS_EG = 4.1;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
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

    public Integer calculateCalories(){
        Integer calories = (int)(fats*PROTEINS_EG + carbohydrates *CARBOHYDRATES_EG + proteins*PROTEINS_EG);
        return calories;
    }


}

