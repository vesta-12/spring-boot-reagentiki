package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.EquipmentRequestDTO;
import com.example.springbootdemo.DTO.EquipmentResponseDTO;
import com.example.springbootdemo.mapper.EquipmentMapper;
import com.example.springbootdemo.model.Equipment;
import com.example.springbootdemo.model.Laboratory;
import com.example.springbootdemo.repository.EquipmentRepository;
import com.example.springbootdemo.repository.LaboratoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final LaboratoryRepository laboratoryRepository;
    private final EquipmentMapper equipmentMapper;

    public List<EquipmentResponseDTO> getAllEquipment() {
        return equipmentRepository.findAll()
                .stream()
                .map(equipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public EquipmentResponseDTO getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .map(equipmentMapper::toDto)
                .orElse(null);
    }

    public List<EquipmentResponseDTO> getEquipmentByLaboratory(Long laboratoryId) {
        return equipmentRepository.findByLaboratoryId(laboratoryId)
                .stream()
                .map(equipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public EquipmentResponseDTO createEquipment(EquipmentRequestDTO dto) {
        Equipment equipment = equipmentMapper.toEntity(dto);

        if (dto.getLaboratoryId() != null) {
            Laboratory laboratory = laboratoryRepository.findById(dto.getLaboratoryId()).orElse(null);
            equipment.setLaboratory(laboratory);
        }

        Equipment saved = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(saved);
    }

    public EquipmentResponseDTO updateEquipment(Long id, EquipmentRequestDTO dto) {
        Equipment existing = equipmentRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(dto.getName());
        existing.setSerialNumber(dto.getSerialNumber());
        existing.setPurchaseDate(dto.getPurchaseDate());
        existing.setStatus(dto.getStatus());

        if (dto.getLaboratoryId() != null) {
            Laboratory laboratory = laboratoryRepository.findById(dto.getLaboratoryId()).orElse(null);
            existing.setLaboratory(laboratory);
        } else {
            existing.setLaboratory(null);
        }

        Equipment saved = equipmentRepository.save(existing);
        return equipmentMapper.toDto(saved);
    }

    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }
}