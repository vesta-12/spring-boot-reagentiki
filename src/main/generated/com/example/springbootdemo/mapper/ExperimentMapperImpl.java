package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ExperimentDTO;
import com.example.springbootdemo.DTO.ResearcherDTO;
import com.example.springbootdemo.model.Experiment;
import com.example.springbootdemo.model.Researcher;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T23:01:46+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (JetBrains s.r.o.)"
)
@Component
public class ExperimentMapperImpl implements ExperimentMapper {

    @Autowired
    private LaboratoryMapper laboratoryMapper;
    @Autowired
    private ResearcherMapper researcherMapper;

    @Override
    public Experiment toEntity(ExperimentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Experiment experiment = new Experiment();

        experiment.setId( dto.getId() );
        experiment.setExperimentName( dto.getExperimentName() );
        experiment.setLeaderName( dto.getLeaderName() );
        experiment.setDate( dto.getDate() );
        experiment.setResultSummary( dto.getResultSummary() );
        experiment.setLaboratory( laboratoryMapper.toEntity( dto.getLaboratory() ) );
        experiment.setResearchers( researcherDTOListToResearcherList( dto.getResearchers() ) );

        return experiment;
    }

    @Override
    public ExperimentDTO toDto(Experiment entity) {
        if ( entity == null ) {
            return null;
        }

        ExperimentDTO experimentDTO = new ExperimentDTO();

        experimentDTO.setId( entity.getId() );
        experimentDTO.setExperimentName( entity.getExperimentName() );
        experimentDTO.setLeaderName( entity.getLeaderName() );
        experimentDTO.setDate( entity.getDate() );
        experimentDTO.setResultSummary( entity.getResultSummary() );
        experimentDTO.setLaboratory( laboratoryMapper.toDto( entity.getLaboratory() ) );
        experimentDTO.setResearchers( researcherListToResearcherDTOList( entity.getResearchers() ) );

        return experimentDTO;
    }

    protected List<Researcher> researcherDTOListToResearcherList(List<ResearcherDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Researcher> list1 = new ArrayList<Researcher>( list.size() );
        for ( ResearcherDTO researcherDTO : list ) {
            list1.add( researcherMapper.toEntity( researcherDTO ) );
        }

        return list1;
    }

    protected List<ResearcherDTO> researcherListToResearcherDTOList(List<Researcher> list) {
        if ( list == null ) {
            return null;
        }

        List<ResearcherDTO> list1 = new ArrayList<ResearcherDTO>( list.size() );
        for ( Researcher researcher : list ) {
            list1.add( researcherMapper.toDto( researcher ) );
        }

        return list1;
    }
}
