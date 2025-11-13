package com.example.springbootdemo.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentDTO {
    private Long id;
    private String experimentName;
    private String researcherName;
    private LocalDate date;
    private String resultSummary;
}