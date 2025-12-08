package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.CountryDto;
import com.example.springbootdemo.model.Country;
import com.example.springbootdemo.repository.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class CountryMapperTest {
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private CountryRepository countryRepository;
    @Test
    public void toDtoTest() {
        Country entity = new Country();
        entity.setName("Sweden");
        entity.setCode("SW");
        CountryDto countryDto = countryMapper.toDto(entity);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(countryDto),
                () -> Assertions.assertNotNull(countryDto.getCode()),
                () -> Assertions.assertNotNull(countryDto.getName()),
                () -> Assertions.assertEquals(entity.getCode(), countryDto.getCode()),
                () -> Assertions.assertEquals(entity.getName(), countryDto.getName()),
                () -> Assertions.assertEquals(entity.getId(), countryDto.getId())
        );
    }
    @Test
    public void toEntityTest() {
        CountryDto countryDto = new CountryDto();
        countryDto.setName("Sweden");
        countryDto.setCode("SW");
        Country entity = countryMapper.toEntity(countryDto);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(entity),
                () -> Assertions.assertNotNull(entity.getCode()),
                () -> Assertions.assertNotNull(entity.getName()),
                () -> Assertions.assertEquals(entity.getCode(), countryDto.getCode()),
                () -> Assertions.assertEquals(entity.getName(), countryDto.getName()),
                () -> Assertions.assertEquals(entity.getId(), countryDto.getId())
        );
    }
    @Test
    public void toDtoListTest() {
        List<Country> entities = countryRepository.findAll();
        List<CountryDto> list = countryMapper.toDtoList(entities);
        for (int i = 0; i < list.size(); i++) {
            CountryDto countryDto = list.get(i);
            Country entity = entities.get(i);
            Assertions.assertAll(
                    () -> Assertions.assertNotNull(countryDto),
                    () -> Assertions.assertNotNull(countryDto.getCode()),
                    () -> Assertions.assertNotNull(countryDto.getName()),
                    () -> Assertions.assertEquals(entity.getCode(), countryDto.getCode()),
                    () -> Assertions.assertEquals(entity.getName(), countryDto.getName()),
                    () -> Assertions.assertEquals(entity.getId(), countryDto.getId())
            );
        }
    }
}
