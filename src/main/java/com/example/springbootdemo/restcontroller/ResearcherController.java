package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.ResearcherDTO;
import com.example.springbootdemo.service.ResearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/researchers")
@RequiredArgsConstructor
public class ResearcherController {
    private final ResearcherService researcherService;

    @GetMapping
    public ResponseEntity<List<ResearcherDTO>> getAllResearchers() {
        List<ResearcherDTO> researchers = researcherService.getAllResearchers();
        return researchers.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(researchers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearcherDTO> getResearcherById(@PathVariable Long id) {
        ResearcherDTO researcher = researcherService.getResearcherById(id);
        return researcher != null
                ? ResponseEntity.ok(researcher)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ResearcherDTO> createResearcher(@RequestBody ResearcherDTO dto) {
        ResearcherDTO created = researcherService.createResearcher(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResearcherDTO> updateResearcher(@PathVariable Long id, @RequestBody ResearcherDTO dto) {
        ResearcherDTO updated = researcherService.updateResearcher(id, dto);
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