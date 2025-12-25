package com.example.springbootdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentRequestDTO {
    private String experimentName;
    private String leaderName;
    private LocalDate date;
    private String reagentsUsed;
    private String method;
    private String resultSummary;
    private Long laboratoryId;
    private List<Long> researcherIds;
}