package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ExperimentRequestDTO;
import com.example.springbootdemo.DTO.ExperimentResponseDTO;
import com.example.springbootdemo.model.Experiment;
import com.example.springbootdemo.model.Researcher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ExperimentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "laboratory", ignore = true)
    @Mapping(target = "researchers", ignore = true)
    Experiment toEntity(ExperimentRequestDTO dto);

    @Mapping(target = "laboratoryId", expression = "java(experiment.getLaboratory() != null ? experiment.getLaboratory().getId() : null)")
    @Mapping(target = "researcherIds", expression = "java(mapResearcherIds(experiment.getResearchers()))")
    ExperimentResponseDTO toDto(Experiment experiment);

    default List<Long> mapResearcherIds(List<Researcher> researchers) {
        if (researchers == null) return null;
        return researchers.stream().map(Researcher::getId).toList();
    }
}