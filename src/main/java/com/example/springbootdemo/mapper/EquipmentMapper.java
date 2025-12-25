package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.EquipmentRequestDTO;
import com.example.springbootdemo.DTO.EquipmentResponseDTO;
import com.example.springbootdemo.model.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "laboratory", ignore = true)
    Equipment toEntity(EquipmentRequestDTO dto);

    @Mapping(target = "laboratoryId", expression = "java(equipment.getLaboratory() != null ? equipment.getLaboratory().getId() : null)")
    EquipmentResponseDTO toDto(Equipment equipment);
}
