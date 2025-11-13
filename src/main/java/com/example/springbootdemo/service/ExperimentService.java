package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.ExperimentDTO;
import com.example.springbootdemo.model.Experiment;
import com.example.springbootdemo.repository.ExperimentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperimentService {

    private final ExperimentRepository experimentRepository;

    public ExperimentDTO toDto(Experiment experiment) {
        return new ExperimentDTO(
                experiment.getId(),
                experiment.getExperimentName(),
                experiment.getResearcherName(),
                experiment.getDate(),
                experiment.getResultSummary()
        );
    }

    public Experiment toEntity(ExperimentDTO dto) {
        Experiment experiment = new Experiment();
        experiment.setId(dto.getId());
        experiment.setExperimentName(dto.getExperimentName());
        experiment.setResearcherName(dto.getResearcherName());
        experiment.setDate(dto.getDate());
        experiment.setResultSummary(dto.getResultSummary());
        experiment.setMethod("");
        experiment.setReagentsUsed("");
        return experiment;
    }

    public List<ExperimentDTO> getAllExperiments() {
        return experimentRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ExperimentDTO getExperimentById(Long id) {
        return experimentRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public ExperimentDTO createExperiment(ExperimentDTO dto) {
        Experiment experiment = toEntity(dto);
        return toDto(experimentRepository.save(experiment));
    }

    public ExperimentDTO updateExperiment(Long id, ExperimentDTO dto) {
        Experiment existing = experimentRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setExperimentName(dto.getExperimentName());
        existing.setResearcherName(dto.getResearcherName());
        existing.setDate(dto.getDate());
        existing.setResultSummary(dto.getResultSummary());

        return toDto(experimentRepository.save(existing));
    }

    public void deleteExperiment(Long id) {
        experimentRepository.deleteById(id);
    }
}