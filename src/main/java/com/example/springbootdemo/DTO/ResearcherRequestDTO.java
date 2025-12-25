package com.example.springbootdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResearcherRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String specialization;
    private String phoneNumber;
}
