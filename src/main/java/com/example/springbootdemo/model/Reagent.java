package com.example.springbootdemo.model;
import lombok.*;

@Data
@AllArgsConstructor
public class Reagent {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
}