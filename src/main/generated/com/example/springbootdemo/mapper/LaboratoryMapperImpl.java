package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.LaboratoryRequestDTO;
import com.example.springbootdemo.DTO.LaboratoryResponseDTO;
import com.example.springbootdemo.model.Laboratory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-25T15:59:06+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (JetBrains s.r.o.)"
)
@Component
public class LaboratoryMapperImpl implements LaboratoryMapper {

    @Override
    public Laboratory toEntity(LaboratoryRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Laboratory laboratory = new Laboratory();

        laboratory.setName( dto.getName() );
        laboratory.setLocation( dto.getLocation() );
        laboratory.setDepartment( dto.getDepartment() );
        laboratory.setEquipmentDescription( dto.getEquipmentDescription() );

        return laboratory;
    }

    @Override
    public LaboratoryResponseDTO toDto(Laboratory laboratory) {
        if ( laboratory == null ) {
            return null;
        }

        LaboratoryResponseDTO laboratoryResponseDTO = new LaboratoryResponseDTO();

        laboratoryResponseDTO.setId( laboratory.getId() );
        laboratoryResponseDTO.setName( laboratory.getName() );
        laboratoryResponseDTO.setLocation( laboratory.getLocation() );
        laboratoryResponseDTO.setDepartment( laboratory.getDepartment() );
        laboratoryResponseDTO.setEquipmentDescription( laboratory.getEquipmentDescription() );

        return laboratoryResponseDTO;
    }
}
