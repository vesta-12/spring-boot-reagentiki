package com.example.springbootdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "laboratories")
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "department")
    private String department;

    @Column(name = "equipment_description", columnDefinition = "TEXT")
    private String equipmentDescription;

    @OneToMany(mappedBy = "laboratory", cascade = CascadeType.ALL)
    private List<Experiment> experiments = new ArrayList<>();
}