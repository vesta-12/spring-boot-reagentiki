package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.LaboratoryRequestDTO;
import com.example.springbootdemo.DTO.LaboratoryResponseDTO;
import com.example.springbootdemo.service.LaboratoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratories")
@RequiredArgsConstructor
public class LaboratoryController {
    private final LaboratoryService laboratoryService;

    @GetMapping
    public ResponseEntity<List<LaboratoryResponseDTO>> getAllLaboratories() {
        List<LaboratoryResponseDTO> labs = laboratoryService.getAllLaboratories();
        return labs.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(labs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryResponseDTO> getLaboratoryById(@PathVariable Long id) {
        LaboratoryResponseDTO lab = laboratoryService.getLaboratoryById(id);
        return lab != null
                ? ResponseEntity.ok(lab)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LaboratoryResponseDTO> createLaboratory(@RequestBody LaboratoryRequestDTO dto) {
        LaboratoryResponseDTO created = laboratoryService.createLaboratory(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratoryResponseDTO> updateLaboratory(@PathVariable Long id, @RequestBody LaboratoryRequestDTO dto) {
        LaboratoryResponseDTO updated = laboratoryService.updateLaboratory(id, dto);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratory(@PathVariable Long id) {
        laboratoryService.deleteLaboratory(id);
        return ResponseEntity.ok().build();
    }
}