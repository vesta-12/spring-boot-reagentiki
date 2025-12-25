package com.example.springbootdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentRequestDTO {
    private String name;
    private String serialNumber;
    private LocalDate purchaseDate;
    private String status;
    private Long laboratoryId;
}