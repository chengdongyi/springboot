package com.example.java8.domain;

import lombok.Data;

@Data
public class Employee {

    private Integer id;
    private String name;
    private Integer age;
    private Double salary;

    public Employee() {

    }

    public Employee(Integer id, String name, Integer age, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

}
