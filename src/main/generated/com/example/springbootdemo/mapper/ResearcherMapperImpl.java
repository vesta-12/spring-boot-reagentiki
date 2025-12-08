package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ResearcherDTO;
import com.example.springbootdemo.model.Researcher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T23:01:46+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (JetBrains s.r.o.)"
)
@Component
public class ResearcherMapperImpl implements ResearcherMapper {

    @Override
    public Researcher toEntity(ResearcherDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Researcher researcher = new Researcher();

        researcher.setId( dto.getId() );
        researcher.setFirstName( dto.getFirstName() );
        researcher.setLastName( dto.getLastName() );
        researcher.setEmail( dto.getEmail() );
        researcher.setSpecialization( dto.getSpecialization() );

        return researcher;
    }

    @Override
    public ResearcherDTO toDto(Researcher entity) {
        if ( entity == null ) {
            return null;
        }

        ResearcherDTO researcherDTO = new ResearcherDTO();

        researcherDTO.setId( entity.getId() );
        researcherDTO.setFirstName( entity.getFirstName() );
        researcherDTO.setLastName( entity.getLastName() );
        researcherDTO.setEmail( entity.getEmail() );
        researcherDTO.setSpecialization( entity.getSpecialization() );

        return researcherDTO;
    }
}
