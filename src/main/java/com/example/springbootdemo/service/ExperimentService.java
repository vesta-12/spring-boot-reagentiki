package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.ExperimentDTO;
import com.example.springbootdemo.mapper.ExperimentMapper;
import com.example.springbootdemo.model.Experiment;
import com.example.springbootdemo.model.Laboratory;
import com.example.springbootdemo.model.Researcher;
import com.example.springbootdemo.repository.ExperimentRepository;
import com.example.springbootdemo.repository.LaboratoryRepository;
import com.example.springbootdemo.repository.ResearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperimentService {

    private final ExperimentRepository experimentRepository;
    private final LaboratoryRepository laboratoryRepository;
    private final ResearcherRepository researcherRepository;
    private final ExperimentMapper experimentMapper;

    public List<ExperimentDTO> getAllExperiments() {
        return experimentRepository.findAll()
                .stream()
                .map(experimentMapper::toDto)
                .collect(Collectors.toList());
    }

    public ExperimentDTO getExperimentById(Long id) {
        return experimentRepository.findById(id)
                .map(experimentMapper::toDto)
                .orElse(null);
    }

    public ExperimentDTO createExperiment(ExperimentDTO dto) {
        Experiment experiment = experimentMapper.toEntity(dto);
        Experiment savedExperiment = experimentRepository.save(experiment);
        return experimentMapper.toDto(savedExperiment);
    }

    public ExperimentDTO updateExperiment(Long id, ExperimentDTO dto) {
        Experiment existing = experimentRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setExperimentName(dto.getExperimentName());
        existing.setLeaderName(dto.getLeaderName());
        existing.setDate(dto.getDate());
        existing.setResultSummary(dto.getResultSummary());

        Experiment updated = experimentRepository.save(existing);
        return experimentMapper.toDto(updated);
    }

    public void deleteExperiment(Long id) {
        experimentRepository.deleteById(id);
    }

    @Transactional
    public ExperimentDTO assignLaboratoryToExperiment(Long experimentId, Long laboratoryId) {
        Experiment experiment = experimentRepository.findById(experimentId).orElse(null);
        Laboratory laboratory = laboratoryRepository.findById(laboratoryId).orElse(null);

        if (experiment != null && laboratory != null) {
            experiment.setLaboratory(laboratory);
            Experiment updated = experimentRepository.save(experiment);
            return experimentMapper.toDto(updated);
        }
        return null;
    }

    @Transactional
    public ExperimentDTO removeLaboratoryFromExperiment(Long experimentId) {
        Experiment experiment = experimentRepository.findById(experimentId).orElse(null);
        if (experiment != null) {
            experiment.setLaboratory(null);
            Experiment updated = experimentRepository.save(experiment);
            return experimentMapper.toDto(updated);
        }
        return null;
    }

    @Transactional
    public ExperimentDTO addResearcherToExperiment(Long experimentId, Long researcherId) {
        Experiment experiment = experimentRepository.findById(experimentId).orElse(null);
        Researcher researcher = researcherRepository.findById(researcherId).orElse(null);

        if (experiment != null && researcher != null) {
            experiment.getResearchers().add(researcher);
            Experiment updated = experimentRepository.save(experiment);
            return experimentMapper.toDto(updated);
        }
        return null;
    }

    @Transactional
    public ExperimentDTO removeResearcherFromExperiment(Long experimentId, Long researcherId) {
        Experiment experiment = experimentRepository.findById(experimentId).orElse(null);
        Researcher researcher = researcherRepository.findById(researcherId).orElse(null);

        if (experiment != null && researcher != null) {
            experiment.getResearchers().remove(researcher);
            Experiment updated = experimentRepository.save(experiment);
            return experimentMapper.toDto(updated);
        }
        return null;
    }

    @Transactional
    public ExperimentDTO updateExperimentResearchers(Long experimentId, List<Long> researcherIds) {
        Experiment experiment = experimentRepository.findById(experimentId).orElse(null);
        if (experiment == null) return null;

        List<Researcher> researchers = researcherRepository.findAllById(researcherIds);
        experiment.getResearchers().clear();
        experiment.getResearchers().addAll(researchers);

        Experiment updated = experimentRepository.save(experiment);
        return experimentMapper.toDto(updated);
    }

    public List<ExperimentDTO> getExperimentsByLaboratory(Long laboratoryId) {
        return experimentRepository.findByLaboratoryId(laboratoryId)
                .stream()
                .map(experimentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ExperimentDTO> getExperimentsByResearcher(Long researcherId) {
        return experimentRepository.findByResearchersId(researcherId)
                .stream()
                .map(experimentMapper::toDto)
                .collect(Collectors.toList());
    }
}