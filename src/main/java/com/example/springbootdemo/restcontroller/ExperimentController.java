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
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
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
}