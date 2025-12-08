package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.CountryDto;
import com.example.springbootdemo.model.Country;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-04T23:01:46+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (JetBrains s.r.o.)"
)
@Component
public class CountryMapperImpl implements CountryMapper {

    @Override
    public CountryDto toDto(Country entity) {
        if ( entity == null ) {
            return null;
        }

        CountryDto countryDto = new CountryDto();

        countryDto.setId( entity.getId() );
        countryDto.setName( entity.getName() );
        countryDto.setCode( entity.getCode() );

        return countryDto;
    }

    @Override
    public Country toEntity(CountryDto dto) {
        if ( dto == null ) {
            return null;
        }

        Country country = new Country();

        country.setId( dto.getId() );
        country.setName( dto.getName() );
        country.setCode( dto.getCode() );

        return country;
    }

    @Override
    public List<CountryDto> toDtoList(List<Country> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CountryDto> list = new ArrayList<CountryDto>( entities.size() );
        for ( Country country : entities ) {
            list.add( toDto( country ) );
        }

        return list;
    }
}
