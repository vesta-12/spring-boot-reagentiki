package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ResearcherRequestDTO;
import com.example.springbootdemo.DTO.ResearcherResponseDTO;
import com.example.springbootdemo.model.Researcher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResearcherMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "experiments", ignore = true)
    Researcher toEntity(ResearcherRequestDTO dto);

    ResearcherResponseDTO toDto(Researcher researcher);
}