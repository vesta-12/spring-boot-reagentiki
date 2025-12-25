package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.ExperimentRequestDTO;
import com.example.springbootdemo.DTO.ExperimentResponseDTO;
import com.example.springbootdemo.service.ExperimentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/experiments")
@RequiredArgsConstructor
public class ExperimentController {

    private final ExperimentService experimentService;

    @GetMapping
    public ResponseEntity<List<ExperimentResponseDTO>> getAllExperiments() {
        List<ExperimentResponseDTO> experiments = experimentService.getAllExperiments();
        return experiments.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(experiments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperimentResponseDTO> getExperimentById(@PathVariable Long id) {
        ExperimentResponseDTO experiment = experimentService.getExperimentById(id);
        return experiment != null
                ? ResponseEntity.ok(experiment)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExperimentResponseDTO> createExperiment(@RequestBody ExperimentRequestDTO dto) {
        ExperimentResponseDTO created = experimentService.createExperiment(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperimentResponseDTO> updateExperiment(@PathVariable Long id, @RequestBody ExperimentRequestDTO dto) {
        ExperimentResponseDTO updated = experimentService.updateExperiment(id, dto);
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
    public ResponseEntity<ExperimentResponseDTO> assignLaboratory(
            @PathVariable Long experimentId,
            @PathVariable Long laboratoryId
    ) {
        ExperimentResponseDTO updated = experimentService.assignLaboratoryToExperiment(experimentId, laboratoryId);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{experimentId}/laboratory")
    public ResponseEntity<ExperimentResponseDTO> removeLaboratory(@PathVariable Long experimentId) {
        ExperimentResponseDTO updated = experimentService.removeLaboratoryFromExperiment(experimentId);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{experimentId}/researchers/{researcherId}")
    public ResponseEntity<ExperimentResponseDTO> addResearcher(
            @PathVariable Long experimentId,
            @PathVariable Long researcherId
    ) {
        ExperimentResponseDTO updated = experimentService.addResearcherToExperiment(experimentId, researcherId);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{experimentId}/researchers/{researcherId}")
    public ResponseEntity<ExperimentResponseDTO> removeResearcher(
            @PathVariable Long experimentId,
            @PathVariable Long researcherId
    ) {
        ExperimentResponseDTO updated = experimentService.removeResearcherFromExperiment(experimentId, researcherId);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{experimentId}/researchers")
    public ResponseEntity<ExperimentResponseDTO> updateResearchers(
            @PathVariable Long experimentId,
            @RequestBody List<Long> researcherIds
    ) {
        ExperimentResponseDTO updated = experimentService.updateExperimentResearchers(experimentId, researcherIds);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/laboratory/{laboratoryId}")
    public ResponseEntity<List<ExperimentResponseDTO>> getByLaboratory(@PathVariable Long laboratoryId) {
        List<ExperimentResponseDTO> experiments = experimentService.getExperimentsByLaboratory(laboratoryId);
        return experiments.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(experiments);
    }

    @GetMapping("/researcher/{researcherId}")
    public ResponseEntity<List<ExperimentResponseDTO>> getByResearcher(@PathVariable Long researcherId) {
        List<ExperimentResponseDTO> experiments = experimentService.getExperimentsByResearcher(researcherId);
        return experiments.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(experiments);
    }
}