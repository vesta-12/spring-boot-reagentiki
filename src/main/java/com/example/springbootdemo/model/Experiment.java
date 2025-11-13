package com.example.springbootdemo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "experiments")
public class Experiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "experiment_name", nullable = false)
    private String experimentName;

    @Column(name = "researcher_name", nullable = false)
    private String researcherName;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "reagents_used", columnDefinition = "TEXT")
    private String reagentsUsed;

    @Column(name = "method", columnDefinition = "TEXT")
    private String method;

    @Column(name = "result_summary", columnDefinition = "TEXT")
    private String resultSummary;
}