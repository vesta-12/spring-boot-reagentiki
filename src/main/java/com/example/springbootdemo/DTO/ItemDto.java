package com.example.springbootdemo.DTO;
import lombok.*;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private String name;
    private int price;
    private int quantity;

    private Long manufacturerId;
    private String manufacturerName;
}