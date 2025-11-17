package com.example.springbootdemo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryDTO {
    private Long id;
    private String name;
    private String location;
    private String department;
}