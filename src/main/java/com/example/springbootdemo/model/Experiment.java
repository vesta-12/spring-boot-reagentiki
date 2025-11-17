package com.example.springbootdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private String leaderName;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "reagents_used", columnDefinition = "TEXT")
    private String reagentsUsed;

    @Column(name = "method", columnDefinition = "TEXT")
    private String method;

    @Column(name = "result_summary", columnDefinition = "TEXT")
    private String resultSummary;

    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;

    @ManyToMany
    @JoinTable(
            name = "experiment_researchers",
            joinColumns = @JoinColumn(name = "experiment_id"),
            inverseJoinColumns = @JoinColumn(name = "researcher_id")
    )
    private List<Researcher> researchers = new ArrayList<>();
}