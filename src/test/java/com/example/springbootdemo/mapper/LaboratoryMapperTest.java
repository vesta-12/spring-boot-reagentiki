package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.LaboratoryRequestDTO;
import com.example.springbootdemo.DTO.LaboratoryResponseDTO;
import com.example.springbootdemo.model.Laboratory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class LaboratoryMapperTest {

    @Autowired
    private LaboratoryMapper laboratoryMapper;

    @Test
    public void toDtoTest() {
        Laboratory entity = new Laboratory();
        entity.setId(1L);
        entity.setName("Central Lab");
        entity.setLocation("Almaty");
        entity.setDepartment("Chemistry");
        entity.setEquipmentDescription("Balances, fume hoods");

        LaboratoryResponseDTO dto = laboratoryMapper.toDto(entity);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dto),
                () -> Assertions.assertEquals(entity.getId(), dto.getId()),
                () -> Assertions.assertEquals(entity.getName(), dto.getName()),
                () -> Assertions.assertEquals(entity.getLocation(), dto.getLocation()),
                () -> Assertions.assertEquals(entity.getDepartment(), dto.getDepartment()),
                () -> Assertions.assertEquals(entity.getEquipmentDescription(), dto.getEquipmentDescription())
        );
    }

    @Test
    public void toEntityTest() {
        LaboratoryRequestDTO req = new LaboratoryRequestDTO();
        req.setName("BioTech Lab");
        req.setLocation("Astana");
        req.setDepartment("Biotechnology");
        req.setEquipmentDescription("PCR, incubators");

        Laboratory entity = laboratoryMapper.toEntity(req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(entity),
                () -> Assertions.assertEquals(req.getName(), entity.getName()),
                () -> Assertions.assertEquals(req.getLocation(), entity.getLocation()),
                () -> Assertions.assertEquals(req.getDepartment(), entity.getDepartment()),
                () -> Assertions.assertEquals(req.getEquipmentDescription(), entity.getEquipmentDescription())
        );
    }
}
