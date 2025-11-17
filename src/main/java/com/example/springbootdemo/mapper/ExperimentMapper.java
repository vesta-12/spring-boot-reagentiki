package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ExperimentDTO;
import com.example.springbootdemo.model.Experiment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {LaboratoryMapper.class, ResearcherMapper.class})
public interface ExperimentMapper {
    ExperimentMapper INSTANCE = Mappers.getMapper(ExperimentMapper.class);

    @Mapping(target = "reagentsUsed", ignore = true)
    @Mapping(target = "method", ignore = true)
    Experiment toEntity(ExperimentDTO dto);

    ExperimentDTO toDto(Experiment entity);
}