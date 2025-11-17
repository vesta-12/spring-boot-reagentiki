package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.ExperimentDTO;
import com.example.springbootdemo.service.ExperimentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiments")
@RequiredArgsConstructor
public class ExperimentController {

    private final ExperimentService experimentService;

    @GetMapping
    public ResponseEntity<List<ExperimentDTO>> getAllExperiments() {
        List<ExperimentDTO> experiments = experimentService.getAllExperiments();
        return experiments.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(experiments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperimentDTO> getExperimentById(@PathVariable Long id) {
        ExperimentDTO experiment = experimentService.getExperimentById(id);
        return experiment != null
                ? ResponseEntity.ok(experiment)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExperimentDTO> createExperiment(@RequestBody ExperimentDTO dto) {
        ExperimentDTO created = experimentService.createExperiment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperimentDTO> updateExperiment(
            @PathVariable Long id,
            @RequestBody ExperimentDTO dto
    ) {
        ExperimentDTO updated = experimentService.updateExperiment(id, dto);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperiment(@PathVariable Long id) {
        experimentService.deleteExperiment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{experimentId}/laboratory/{laboratoryId}")
    public ResponseEntity<ExperimentDTO> assignLaboratory(
            @PathVariable Long experimentId,
            @PathVariable Long laboratoryId) {
        ExperimentDTO result = experimentService.assignLaboratoryToExperiment(experimentId, laboratoryId);
        return result != null
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{experimentId}/laboratory")
    public ResponseEntity<ExperimentDTO> removeLaboratory(@PathVariable Long experimentId) {
        ExperimentDTO result = experimentService.removeLaboratoryFromExperiment(experimentId);
        return result != null
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{experimentId}/researchers/{researcherId}")
    public ResponseEntity<ExperimentDTO> addResearcher(
            @PathVariable Long experimentId,
            @PathVariable Long researcherId) {
        ExperimentDTO result = experimentService.addResearcherToExperiment(experimentId, researcherId);
        return result != null
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{experimentId}/researchers/{researcherId}")
    public ResponseEntity<ExperimentDTO> removeResearcher(
            @PathVariable Long experimentId,
            @PathVariable Long researcherId) {
        ExperimentDTO result = experimentService.removeResearcherFromExperiment(experimentId, researcherId);
        return result != null
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{experimentId}/researchers")
    public ResponseEntity<ExperimentDTO> updateResearchers(
            @PathVariable Long experimentId,
            @RequestBody List<Long> researcherIds) {
        ExperimentDTO result = experimentService.updateExperimentResearchers(experimentId, researcherIds);
        return result != null
                ? ResponseEntity.ok(result)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/laboratory/{laboratoryId}")
    public ResponseEntity<List<ExperimentDTO>> getExperimentsByLaboratory(@PathVariable Long laboratoryId) {
        List<ExperimentDTO> experiments = experimentService.getExperimentsByLaboratory(laboratoryId);
        return experiments.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(experiments);
    }

    @GetMapping("/researcher/{researcherId}")
    public ResponseEntity<List<ExperimentDTO>> getExperimentsByResearcher(@PathVariable Long researcherId) {
        List<ExperimentDTO> experiments = experimentService.getExperimentsByResearcher(researcherId);
        return experiments.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(experiments);
    }
}