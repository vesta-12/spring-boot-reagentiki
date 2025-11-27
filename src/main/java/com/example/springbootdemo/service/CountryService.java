package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.CountryDto;
import com.example.springbootdemo.mapper.CountryMapper;
import com.example.springbootdemo.model.Country;
import com.example.springbootdemo.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public List<CountryDto> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.toDtoList(countries);
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }
}