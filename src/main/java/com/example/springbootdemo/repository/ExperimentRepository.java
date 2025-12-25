package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.Experiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Long> {

    List<Experiment> findByLaboratoryId(Long laboratoryId);

    @Query("SELECT DISTINCT e FROM Experiment e LEFT JOIN FETCH e.researchers")
    List<Experiment> findAllWithResearchers();

    @Query("SELECT e FROM Experiment e LEFT JOIN FETCH e.researchers WHERE e.id = :id")
    Optional<Experiment> findByIdWithResearchers(@Param("id") Long id);

    @Query("SELECT e FROM Experiment e JOIN e.researchers r WHERE r.id = :researcherId")
    List<Experiment> findByResearchersId(@Param("researcherId") Long researcherId);
}