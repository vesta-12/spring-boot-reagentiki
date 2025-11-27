package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.CountryDto;
import com.example.springbootdemo.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto toDto(Country entity);

    Country toEntity(CountryDto dto);

    List<CountryDto> toDtoList(List<Country> entities);
}