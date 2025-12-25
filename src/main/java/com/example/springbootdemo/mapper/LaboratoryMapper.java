package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.LaboratoryRequestDTO;
import com.example.springbootdemo.DTO.LaboratoryResponseDTO;
import com.example.springbootdemo.model.Laboratory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LaboratoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "experiments", ignore = true)
    Laboratory toEntity(LaboratoryRequestDTO dto);

    LaboratoryResponseDTO toDto(Laboratory laboratory);
}