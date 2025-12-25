package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.ResearcherRequestDTO;
import com.example.springbootdemo.DTO.ResearcherResponseDTO;
import com.example.springbootdemo.service.ResearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/researchers")
@RequiredArgsConstructor
public class ResearcherController {
    private final ResearcherService researcherService;

    @GetMapping
    public ResponseEntity<List<ResearcherResponseDTO>> getAllResearchers() {
        List<ResearcherResponseDTO> researchers = researcherService.getAllResearchers();
        return researchers.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(researchers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearcherResponseDTO> getResearcherById(@PathVariable Long id) {
        ResearcherResponseDTO researcher = researcherService.getResearcherById(id);
        return researcher != null
                ? ResponseEntity.ok(researcher)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ResearcherResponseDTO> createResearcher(@RequestBody ResearcherRequestDTO dto) {
        ResearcherResponseDTO created = researcherService.createResearcher(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResearcherResponseDTO> updateResearcher(@PathVariable Long id, @RequestBody ResearcherRequestDTO dto) {
        ResearcherResponseDTO updated = researcherService.updateResearcher(id, dto);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResearcher(@PathVariable Long id) {
        researcherService.deleteResearcher(id);
        return ResponseEntity.ok().build();
    }
}