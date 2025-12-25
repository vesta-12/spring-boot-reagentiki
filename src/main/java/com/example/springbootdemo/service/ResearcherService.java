package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.ResearcherRequestDTO;
import com.example.springbootdemo.DTO.ResearcherResponseDTO;
import com.example.springbootdemo.mapper.ResearcherMapper;
import com.example.springbootdemo.model.Researcher;
import com.example.springbootdemo.repository.ResearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResearcherService {
    private final ResearcherRepository researcherRepository;
    private final ResearcherMapper researcherMapper;

    public List<ResearcherResponseDTO> getAllResearchers() {
        return researcherRepository.findAll()
                .stream()
                .map(researcherMapper::toDto)
                .collect(Collectors.toList());
    }

    public ResearcherResponseDTO getResearcherById(Long id) {
        return researcherRepository.findById(id)
                .map(researcherMapper::toDto)
                .orElse(null);
    }

    public ResearcherResponseDTO createResearcher(ResearcherRequestDTO dto) {
        Researcher researcher = researcherMapper.toEntity(dto);
        return researcherMapper.toDto(researcherRepository.save(researcher));
    }

    public ResearcherResponseDTO updateResearcher(Long id, ResearcherRequestDTO dto) {
        Researcher existing = researcherRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setSpecialization(dto.getSpecialization());
        existing.setPhoneNumber(dto.getPhoneNumber());

        return researcherMapper.toDto(researcherRepository.save(existing));
    }

    public void deleteResearcher(Long id) {
        researcherRepository.deleteById(id);
    }
}