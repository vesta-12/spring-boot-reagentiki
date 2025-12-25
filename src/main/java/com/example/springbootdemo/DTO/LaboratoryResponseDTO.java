package com.example.springbootdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryResponseDTO {
    private Long id;
    private String name;
    private String location;
    private String department;
    private String equipmentDescription;
}
