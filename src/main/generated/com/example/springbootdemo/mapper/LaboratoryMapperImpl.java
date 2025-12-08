package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.LaboratoryDTO;
import com.example.springbootdemo.model.Laboratory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T23:01:46+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (JetBrains s.r.o.)"
)
@Component
public class LaboratoryMapperImpl implements LaboratoryMapper {

    @Override
    public Laboratory toEntity(LaboratoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Laboratory laboratory = new Laboratory();

        laboratory.setId( dto.getId() );
        laboratory.setName( dto.getName() );
        laboratory.setLocation( dto.getLocation() );
        laboratory.setDepartment( dto.getDepartment() );

        return laboratory;
    }

    @Override
    public LaboratoryDTO toDto(Laboratory entity) {
        if ( entity == null ) {
            return null;
        }

        LaboratoryDTO laboratoryDTO = new LaboratoryDTO();

        laboratoryDTO.setId( entity.getId() );
        laboratoryDTO.setName( entity.getName() );
        laboratoryDTO.setLocation( entity.getLocation() );
        laboratoryDTO.setDepartment( entity.getDepartment() );

        return laboratoryDTO;
    }
}
