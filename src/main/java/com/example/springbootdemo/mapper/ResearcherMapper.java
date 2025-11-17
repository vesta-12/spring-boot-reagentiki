package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ResearcherDTO;
import com.example.springbootdemo.model.Researcher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResearcherMapper {
    ResearcherMapper INSTANCE = Mappers.getMapper(ResearcherMapper.class);

    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "experiments", ignore = true)
    Researcher toEntity(ResearcherDTO dto);

    ResearcherDTO toDto(Researcher entity);
}