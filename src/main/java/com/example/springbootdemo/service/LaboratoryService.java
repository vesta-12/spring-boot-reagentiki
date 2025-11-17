package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.LaboratoryDTO;
import com.example.springbootdemo.mapper.LaboratoryMapper;
import com.example.springbootdemo.model.Laboratory;
import com.example.springbootdemo.repository.LaboratoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaboratoryService {
    private final LaboratoryRepository laboratoryRepository;
    private final LaboratoryMapper laboratoryMapper;

    public List<LaboratoryDTO> getAllLaboratories() {
        return laboratoryRepository.findAll()
                .stream()
                .map(laboratoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public LaboratoryDTO getLaboratoryById(Long id) {
        return laboratoryRepository.findById(id)
                .map(laboratoryMapper::toDto)
                .orElse(null);
    }

    public LaboratoryDTO createLaboratory(LaboratoryDTO dto) {
        Laboratory laboratory = laboratoryMapper.toEntity(dto);
        return laboratoryMapper.toDto(laboratoryRepository.save(laboratory));
    }

    public LaboratoryDTO updateLaboratory(Long id, LaboratoryDTO dto) {
        Laboratory existing = laboratoryRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(dto.getName());
        existing.setLocation(dto.getLocation());
        existing.setDepartment(dto.getDepartment());

        return laboratoryMapper.toDto(laboratoryRepository.save(existing));
    }

    public void deleteLaboratory(Long id) {
        laboratoryRepository.deleteById(id);
    }
}