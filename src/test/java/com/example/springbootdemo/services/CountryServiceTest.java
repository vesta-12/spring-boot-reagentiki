package com.example.springbootdemo.services;

import com.example.springbootdemo.DTO.CountryDto;
import com.example.springbootdemo.model.Country;
import com.example.springbootdemo.repository.CountryRepository;
import com.example.springbootdemo.service.CountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void getAllCountriesTest() {
        List<CountryDto> countries = countryService.getAllCountries();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(countries),
                () -> Assertions.assertFalse(countries.isEmpty())
        );
    }

    @Test
    public void getCountryByIdTest() {
        Country country = new Country();
        country.setName("Testland");
        country.setCode("TST");
        Country saved = countryRepository.save(country);

        CountryDto dto = countryService.getCountryById(saved.getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dto),
                () -> Assertions.assertEquals(saved.getId(), dto.getId()),
                () -> Assertions.assertEquals(saved.getName(), dto.getName()),
                () -> Assertions.assertEquals(saved.getCode(), dto.getCode())
        );
    }

    @Test
    public void createCountryTest() {
        CountryDto dto = new CountryDto();
        dto.setName("NewCountry");
        dto.setCode("NEW");

        CountryDto created = countryService.createCountry(dto);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(created),
                () -> Assertions.assertNotNull(created.getId()),
                () -> Assertions.assertEquals(dto.getName(), created.getName()),
                () -> Assertions.assertEquals(dto.getCode(), created.getCode())
        );

        Country fromDb = countryRepository.findById(created.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertEquals(created.getName(), fromDb.getName()),
                () -> Assertions.assertEquals(created.getCode(), fromDb.getCode())
        );
    }

    @Test
    public void updateCountryTest() {
        Country country = new Country();
        country.setName("OldName");
        country.setCode("OLD");
        Country saved = countryRepository.save(country);

        CountryDto dto = new CountryDto();
        dto.setName("UpdatedName");
        dto.setCode("UPD");

        CountryDto updated = countryService.updateCountry(saved.getId(), dto);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(updated),
                () -> Assertions.assertEquals(saved.getId(), updated.getId()),
                () -> Assertions.assertEquals("UpdatedName", updated.getName()),
                () -> Assertions.assertEquals("UPD", updated.getCode())
        );

        Country fromDb = countryRepository.findById(saved.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertEquals("UpdatedName", fromDb.getName()),
                () -> Assertions.assertEquals("UPD", fromDb.getCode())
        );
    }

    @Test
    public void deleteCountryTest() {
        Country country = new Country();
        country.setName("ToDelete");
        country.setCode("DEL");
        Country saved = countryRepository.save(country);

        boolean result = countryService.deleteCountry(saved.getId());

        Assertions.assertAll(
                () -> Assertions.assertTrue(result),
                () -> Assertions.assertTrue(countryRepository.findById(saved.getId()).isEmpty())
        );
    }
}
