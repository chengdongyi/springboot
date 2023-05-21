package com.example.java8.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private Integer num;
    private Double price;
    private String name;

    /**
     * 种类
     */
    private String category;

}