package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ResearcherRequestDTO;
import com.example.springbootdemo.DTO.ResearcherResponseDTO;
import com.example.springbootdemo.model.Researcher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-25T19:09:04+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (JetBrains s.r.o.)"
)
@Component
public class ResearcherMapperImpl implements ResearcherMapper {

    @Override
    public Researcher toEntity(ResearcherRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Researcher researcher = new Researcher();

        researcher.setFirstName( dto.getFirstName() );
        researcher.setLastName( dto.getLastName() );
        researcher.setEmail( dto.getEmail() );
        researcher.setSpecialization( dto.getSpecialization() );
        researcher.setPhoneNumber( dto.getPhoneNumber() );

        return researcher;
    }

    @Override
    public ResearcherResponseDTO toDto(Researcher researcher) {
        if ( researcher == null ) {
            return null;
        }

        ResearcherResponseDTO researcherResponseDTO = new ResearcherResponseDTO();

        researcherResponseDTO.setId( researcher.getId() );
        researcherResponseDTO.setFirstName( researcher.getFirstName() );
        researcherResponseDTO.setLastName( researcher.getLastName() );
        researcherResponseDTO.setEmail( researcher.getEmail() );
        researcherResponseDTO.setSpecialization( researcher.getSpecialization() );
        researcherResponseDTO.setPhoneNumber( researcher.getPhoneNumber() );

        return researcherResponseDTO;
    }
}
