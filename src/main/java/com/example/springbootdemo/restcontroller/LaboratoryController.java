package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.LaboratoryDTO;
import com.example.springbootdemo.service.LaboratoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratories")
@RequiredArgsConstructor
public class LaboratoryController {
    private final LaboratoryService laboratoryService;

    @GetMapping
    public ResponseEntity<List<LaboratoryDTO>> getAllLaboratories() {
        List<LaboratoryDTO> laboratories = laboratoryService.getAllLaboratories();
        return laboratories.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(laboratories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryDTO> getLaboratoryById(@PathVariable Long id) {
        LaboratoryDTO laboratory = laboratoryService.getLaboratoryById(id);
        return laboratory != null
                ? ResponseEntity.ok(laboratory)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LaboratoryDTO> createLaboratory(@RequestBody LaboratoryDTO dto) {
        LaboratoryDTO created = laboratoryService.createLaboratory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratoryDTO> updateLaboratory(@PathVariable Long id, @RequestBody LaboratoryDTO dto) {
        LaboratoryDTO updated = laboratoryService.updateLaboratory(id, dto);
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