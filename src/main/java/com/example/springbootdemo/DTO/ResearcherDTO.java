package com.example.springbootdemo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResearcherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String specialization;
}