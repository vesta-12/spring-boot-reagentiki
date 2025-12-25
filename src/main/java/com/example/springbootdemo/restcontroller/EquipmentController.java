package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.EquipmentRequestDTO;
import com.example.springbootdemo.DTO.EquipmentResponseDTO;
import com.example.springbootdemo.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<EquipmentResponseDTO>> getAllEquipment() {
        List<EquipmentResponseDTO> equipment = equipmentService.getAllEquipment();
        return equipment.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(equipment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponseDTO> getEquipmentById(@PathVariable Long id) {
        EquipmentResponseDTO equipment = equipmentService.getEquipmentById(id);
        return equipment != null
                ? ResponseEntity.ok(equipment)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/laboratory/{laboratoryId}")
    public ResponseEntity<List<EquipmentResponseDTO>> getEquipmentByLaboratory(@PathVariable Long laboratoryId) {
        List<EquipmentResponseDTO> equipment = equipmentService.getEquipmentByLaboratory(laboratoryId);
        return equipment.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(equipment);
    }

    @PostMapping
    public ResponseEntity<EquipmentResponseDTO> createEquipment(@RequestBody EquipmentRequestDTO dto) {
        EquipmentResponseDTO created = equipmentService.createEquipment(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentResponseDTO> updateEquipment(@PathVariable Long id, @RequestBody EquipmentRequestDTO dto) {
        EquipmentResponseDTO updated = equipmentService.updateEquipment(id, dto);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.ok().build();
    }
}