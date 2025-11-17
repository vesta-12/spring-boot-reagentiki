package com.example.springbootdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentDTO {
    private Long id;
    private String experimentName;
    private String leaderName;
    private LocalDate date;
    private String resultSummary;
    private LaboratoryDTO laboratory;
    private List<ResearcherDTO> researchers;
}