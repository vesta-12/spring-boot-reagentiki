package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.PublicationRequestDTO;
import com.example.springbootdemo.DTO.PublicationResponseDTO;
import com.example.springbootdemo.mapper.PublicationMapper;
import com.example.springbootdemo.model.Experiment;
import com.example.springbootdemo.model.Publication;
import com.example.springbootdemo.repository.ExperimentRepository;
import com.example.springbootdemo.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final ExperimentRepository experimentRepository;
    private final PublicationMapper publicationMapper;

    public List<PublicationResponseDTO> getAllPublications() {
        return publicationRepository.findAll()
                .stream()
                .map(publicationMapper::toDto)
                .collect(Collectors.toList());
    }

    public PublicationResponseDTO getPublicationById(Long id) {
        return publicationRepository.findById(id)
                .map(publicationMapper::toDto)
                .orElse(null);
    }

    public List<PublicationResponseDTO> getPublicationsByExperiment(Long experimentId) {
        return publicationRepository.findByExperimentId(experimentId)
                .stream()
                .map(publicationMapper::toDto)
                .collect(Collectors.toList());
    }

    public PublicationResponseDTO createPublication(PublicationRequestDTO dto) {
        Publication publication = publicationMapper.toEntity(dto);

        if (dto.getExperimentId() != null) {
            Experiment experiment = experimentRepository.findById(dto.getExperimentId()).orElse(null);
            publication.setExperiment(experiment);
        }

        Publication saved = publicationRepository.save(publication);
        return publicationMapper.toDto(saved);
    }

    public PublicationResponseDTO updatePublication(Long id, PublicationRequestDTO dto) {
        Publication existing = publicationRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setTitle(dto.getTitle());
        existing.setJournal(dto.getJournal());
        existing.setPublishedDate(dto.getPublishedDate());
        existing.setDoi(dto.getDoi());

        if (dto.getExperimentId() != null) {
            Experiment experiment = experimentRepository.findById(dto.getExperimentId()).orElse(null);
            existing.setExperiment(experiment);
        } else {
            existing.setExperiment(null);
        }

        Publication saved = publicationRepository.save(existing);
        return publicationMapper.toDto(saved);
    }

    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }
}
