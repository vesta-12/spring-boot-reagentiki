package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.LaboratoryDTO;
import com.example.springbootdemo.model.Laboratory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LaboratoryMapper {
    LaboratoryMapper INSTANCE = Mappers.getMapper(LaboratoryMapper.class);

    @Mapping(target = "equipmentDescription", ignore = true)
    @Mapping(target = "experiments", ignore = true)
    Laboratory toEntity(LaboratoryDTO dto);

    LaboratoryDTO toDto(Laboratory entity);
}