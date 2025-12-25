package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.LaboratoryRequestDTO;
import com.example.springbootdemo.DTO.LaboratoryResponseDTO;
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

    public List<LaboratoryResponseDTO> getAllLaboratories() {
        return laboratoryRepository.findAll()
                .stream()
                .map(laboratoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public LaboratoryResponseDTO getLaboratoryById(Long id) {
        return laboratoryRepository.findById(id)
                .map(laboratoryMapper::toDto)
                .orElse(null);
    }

    public LaboratoryResponseDTO createLaboratory(LaboratoryRequestDTO dto) {
        Laboratory laboratory = laboratoryMapper.toEntity(dto);
        return laboratoryMapper.toDto(laboratoryRepository.save(laboratory));
    }

    public LaboratoryResponseDTO updateLaboratory(Long id, LaboratoryRequestDTO dto) {
        Laboratory existing = laboratoryRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(dto.getName());
        existing.setLocation(dto.getLocation());
        existing.setDepartment(dto.getDepartment());
        existing.setEquipmentDescription(dto.getEquipmentDescription());

        return laboratoryMapper.toDto(laboratoryRepository.save(existing));
    }

    public void deleteLaboratory(Long id) {
        laboratoryRepository.deleteById(id);
    }
}
