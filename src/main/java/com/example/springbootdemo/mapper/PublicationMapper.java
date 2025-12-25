package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.PublicationRequestDTO;
import com.example.springbootdemo.DTO.PublicationResponseDTO;
import com.example.springbootdemo.model.Publication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "experiment", ignore = true)
    Publication toEntity(PublicationRequestDTO dto);

    @Mapping(target = "experimentId", expression = "java(publication.getExperiment() != null ? publication.getExperiment().getId() : null)")
    PublicationResponseDTO toDto(Publication publication);
}

