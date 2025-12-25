package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ExperimentRequestDTO;
import com.example.springbootdemo.DTO.ExperimentResponseDTO;
import com.example.springbootdemo.model.Experiment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-25T15:59:06+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (JetBrains s.r.o.)"
)
@Component
public class ExperimentMapperImpl implements ExperimentMapper {

    @Override
    public Experiment toEntity(ExperimentRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Experiment experiment = new Experiment();

        experiment.setExperimentName( dto.getExperimentName() );
        experiment.setLeaderName( dto.getLeaderName() );
        experiment.setDate( dto.getDate() );
        experiment.setReagentsUsed( dto.getReagentsUsed() );
        experiment.setMethod( dto.getMethod() );
        experiment.setResultSummary( dto.getResultSummary() );

        return experiment;
    }

    @Override
    public ExperimentResponseDTO toDto(Experiment experiment) {
        if ( experiment == null ) {
            return null;
        }

        ExperimentResponseDTO experimentResponseDTO = new ExperimentResponseDTO();

        experimentResponseDTO.setId( experiment.getId() );
        experimentResponseDTO.setExperimentName( experiment.getExperimentName() );
        experimentResponseDTO.setLeaderName( experiment.getLeaderName() );
        experimentResponseDTO.setDate( experiment.getDate() );
        experimentResponseDTO.setReagentsUsed( experiment.getReagentsUsed() );
        experimentResponseDTO.setMethod( experiment.getMethod() );
        experimentResponseDTO.setResultSummary( experiment.getResultSummary() );

        experimentResponseDTO.setLaboratoryId( experiment.getLaboratory() != null ? experiment.getLaboratory().getId() : null );
        experimentResponseDTO.setResearcherIds( mapResearcherIds(experiment.getResearchers()) );

        return experimentResponseDTO;
    }
}
