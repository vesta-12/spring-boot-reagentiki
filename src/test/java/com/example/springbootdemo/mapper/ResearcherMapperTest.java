package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ResearcherRequestDTO;
import com.example.springbootdemo.DTO.ResearcherResponseDTO;
import com.example.springbootdemo.model.Researcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ResearcherMapperTest {

    @Autowired
    private ResearcherMapper researcherMapper;

    @Test
    public void toDtoTest() {
        Researcher entity = new Researcher();
        entity.setId(10L);
        entity.setFirstName("Aruzhan");
        entity.setLastName("Nurzhanova");
        entity.setEmail("aruzhan@uni.kz");
        entity.setSpecialization("Analytical Chemistry");
        entity.setPhoneNumber("+7-701-111-22-33");

        ResearcherResponseDTO dto = researcherMapper.toDto(entity);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dto),
                () -> Assertions.assertEquals(entity.getId(), dto.getId()),
                () -> Assertions.assertEquals(entity.getFirstName(), dto.getFirstName()),
                () -> Assertions.assertEquals(entity.getLastName(), dto.getLastName()),
                () -> Assertions.assertEquals(entity.getEmail(), dto.getEmail()),
                () -> Assertions.assertEquals(entity.getSpecialization(), dto.getSpecialization()),
                () -> Assertions.assertEquals(entity.getPhoneNumber(), dto.getPhoneNumber())
        );
    }

    @Test
    public void toEntityTest() {
        ResearcherRequestDTO req = new ResearcherRequestDTO();
        req.setFirstName("Dias");
        req.setLastName("Sadykov");
        req.setEmail("dias@uni.kz");
        req.setSpecialization("Biochemistry");
        req.setPhoneNumber("+7-702-222-33-44");

        Researcher entity = researcherMapper.toEntity(req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(entity),
                () -> Assertions.assertEquals(req.getFirstName(), entity.getFirstName()),
                () -> Assertions.assertEquals(req.getLastName(), entity.getLastName()),
                () -> Assertions.assertEquals(req.getEmail(), entity.getEmail()),
                () -> Assertions.assertEquals(req.getSpecialization(), entity.getSpecialization()),
                () -> Assertions.assertEquals(req.getPhoneNumber(), entity.getPhoneNumber())
        );
    }
}