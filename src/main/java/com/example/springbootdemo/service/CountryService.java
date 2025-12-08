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

    public CountryDto getCountryById(Long id) {
        Country country = countryRepository.findById(id).orElse(null);
        if (country == null) {
            return null;
        }
        return countryMapper.toDto(country);
    }

    public CountryDto createCountry(CountryDto dto) {
        Country entity = countryMapper.toEntity(dto);
        Country saved = countryRepository.save(entity);
        return countryMapper.toDto(saved);
    }

    public CountryDto updateCountry(Long id, CountryDto dto) {
        Country country = countryRepository.findById(id).orElse(null);
        if (country == null) {
            return null;
        }
        country.setName(dto.getName());
        country.setCode(dto.getCode());
        Country saved = countryRepository.save(country);
        return countryMapper.toDto(saved);
    }

    public boolean deleteCountry(Long id) {
        Country country = countryRepository.findById(id).orElse(null);
        if (country == null) {
            return false;
        }
        countryRepository.delete(country);
        return true;
    }
}
