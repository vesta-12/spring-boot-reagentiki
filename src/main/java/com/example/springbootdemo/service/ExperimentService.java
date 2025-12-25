package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.ExperimentRequestDTO;
import com.example.springbootdemo.DTO.ExperimentResponseDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperimentService {

    private final ExperimentRepository experimentRepository;
    private final LaboratoryRepository laboratoryRepository;
    private final ResearcherRepository researcherRepository;
    private final ExperimentMapper experimentMapper;

    public List<ExperimentResponseDTO> getAllExperiments() {
        return experimentRepository.findAllWithResearchers()
                .stream()
                .map(experimentMapper::toDto)
                .toList();
    }

    public ExperimentResponseDTO getExperimentById(Long id) {
        return experimentRepository.findByIdWithResearchers(id)
                .map(experimentMapper::toDto)
                .orElse(null);
    }

    public ExperimentResponseDTO createExperiment(ExperimentRequestDTO dto) {
        Experiment experiment = experimentMapper.toEntity(dto);

        if (dto.getLaboratoryId() != null) {
            Laboratory lab = laboratoryRepository.findById(dto.getLaboratoryId()).orElse(null);
            experiment.setLaboratory(lab);
        }

        if (dto.getResearcherIds() != null) {
            List<Researcher> researchers = researcherRepository.findAllById(dto.getResearcherIds());
            experiment.setResearchers(new ArrayList<>(researchers));
        }

        Experiment saved = experimentRepository.save(experiment);
        return experimentMapper.toDto(saved);
    }

    public ExperimentResponseDTO updateExperiment(Long id, ExperimentRequestDTO dto) {
        Experiment existing = experimentRepository.findByIdWithResearchers(id).orElse(null);
        if (existing == null) return null;

        existing.setExperimentName(dto.getExperimentName());
        existing.setLeaderName(dto.getLeaderName());
        existing.setDate(dto.getDate());
        existing.setReagentsUsed(dto.getReagentsUsed());
        existing.setMethod(dto.getMethod());
        existing.setResultSummary(dto.getResultSummary());

        if (dto.getLaboratoryId() != null) {
            Laboratory lab = laboratoryRepository.findById(dto.getLaboratoryId()).orElse(null);
            existing.setLaboratory(lab);
        } else if (dto.getLaboratoryId() == null) {
            // если хочешь оставить лабораторию как есть — убери эту строку
            existing.setLaboratory(null);
        }

        if (dto.getResearcherIds() != null) {
            List<Researcher> researchers = researcherRepository.findAllById(dto.getResearcherIds());
            existing.getResearchers().clear();
            existing.getResearchers().addAll(researchers);
        }

        Experiment updated = experimentRepository.save(existing);
        return experimentMapper.toDto(updated);
    }

    public void deleteExperiment(Long id) {
        experimentRepository.deleteById(id);
    }

    @Transactional
    public ExperimentResponseDTO assignLaboratoryToExperiment(Long experimentId, Long laboratoryId) {
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
    public ExperimentResponseDTO removeLaboratoryFromExperiment(Long experimentId) {
        Experiment experiment = experimentRepository.findById(experimentId).orElse(null);
        if (experiment != null) {
            experiment.setLaboratory(null);
            Experiment updated = experimentRepository.save(experiment);
            return experimentMapper.toDto(updated);
        }
        return null;
    }

    @Transactional
    public ExperimentResponseDTO addResearcherToExperiment(Long experimentId, Long researcherId) {
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
    public ExperimentResponseDTO removeResearcherFromExperiment(Long experimentId, Long researcherId) {
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
    public ExperimentResponseDTO updateExperimentResearchers(Long experimentId, List<Long> researcherIds) {
        Experiment experiment = experimentRepository.findById(experimentId).orElse(null);
        if (experiment == null) return null;

        List<Researcher> researchers = researcherRepository.findAllById(researcherIds);
        experiment.getResearchers().clear();
        experiment.getResearchers().addAll(researchers);

        Experiment updated = experimentRepository.save(experiment);
        return experimentMapper.toDto(updated);
    }

    public List<ExperimentResponseDTO> getExperimentsByLaboratory(Long laboratoryId) {
        return experimentRepository.findByLaboratoryId(laboratoryId)
                .stream()
                .map(experimentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ExperimentResponseDTO> getExperimentsByResearcher(Long researcherId) {
        return experimentRepository.findByResearchersId(researcherId)
                .stream()
                .map(experimentMapper::toDto)
                .collect(Collectors.toList());
    }
}
