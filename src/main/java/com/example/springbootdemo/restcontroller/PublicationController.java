package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.PublicationRequestDTO;
import com.example.springbootdemo.DTO.PublicationResponseDTO;
import com.example.springbootdemo.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/publications")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;

    @GetMapping
    public ResponseEntity<List<PublicationResponseDTO>> getAllPublications() {
        List<PublicationResponseDTO> publications = publicationService.getAllPublications();
        return publications.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(publications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationResponseDTO> getPublicationById(@PathVariable Long id) {
        PublicationResponseDTO publication = publicationService.getPublicationById(id);
        return publication != null
                ? ResponseEntity.ok(publication)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/experiment/{experimentId}")
    public ResponseEntity<List<PublicationResponseDTO>> getPublicationsByExperiment(@PathVariable Long experimentId) {
        List<PublicationResponseDTO> publications = publicationService.getPublicationsByExperiment(experimentId);
        return publications.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(publications);
    }

    @PostMapping
    public ResponseEntity<PublicationResponseDTO> createPublication(@RequestBody PublicationRequestDTO dto) {
        PublicationResponseDTO created = publicationService.createPublication(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationResponseDTO> updatePublication(@PathVariable Long id, @RequestBody PublicationRequestDTO dto) {
        PublicationResponseDTO updated = publicationService.updatePublication(id, dto);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        publicationService.deletePublication(id);
        return ResponseEntity.ok().build();
    }
}