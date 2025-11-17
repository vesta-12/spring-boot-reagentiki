package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Long> {

    List<Experiment> findByLaboratoryId(Long laboratoryId);

    @Query("SELECT e FROM Experiment e JOIN e.researchers r WHERE r.id = :researcherId")
    List<Experiment> findByResearchersId(@Param("researcherId") Long researcherId);
}