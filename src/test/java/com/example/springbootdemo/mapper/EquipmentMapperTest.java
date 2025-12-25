package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.EquipmentRequestDTO;
import com.example.springbootdemo.DTO.EquipmentResponseDTO;
import com.example.springbootdemo.model.Equipment;
import com.example.springbootdemo.model.Laboratory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class EquipmentMapperTest {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Test
    public void toDtoTest() {
        Laboratory lab = new Laboratory();
        lab.setId(2L);

        Equipment entity = new Equipment();
        entity.setId(200L);
        entity.setName("Analytical Balance");
        entity.setSerialNumber("BAL-ALM-2023-001");
        entity.setPurchaseDate(LocalDate.of(2023, 9, 12));
        entity.setStatus("ACTIVE");
        entity.setLaboratory(lab);

        EquipmentResponseDTO dto = equipmentMapper.toDto(entity);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dto),
                () -> Assertions.assertEquals(entity.getId(), dto.getId()),
                () -> Assertions.assertEquals(entity.getName(), dto.getName()),
                () -> Assertions.assertEquals(entity.getSerialNumber(), dto.getSerialNumber()),
                () -> Assertions.assertEquals(entity.getPurchaseDate(), dto.getPurchaseDate()),
                () -> Assertions.assertEquals(entity.getStatus(), dto.getStatus()),
                () -> Assertions.assertEquals(lab.getId(), dto.getLaboratoryId())
        );
    }

    @Test
    public void toEntityTest() {
        EquipmentRequestDTO req = new EquipmentRequestDTO();
        req.setName("Thermal Cycler");
        req.setSerialNumber("PCR-AST-2022-014");
        req.setPurchaseDate(LocalDate.of(2022, 2, 20));
        req.setStatus("ACTIVE");
        req.setLaboratoryId(1L);

        Equipment entity = equipmentMapper.toEntity(req);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(entity),
                () -> Assertions.assertEquals(req.getName(), entity.getName()),
                () -> Assertions.assertEquals(req.getSerialNumber(), entity.getSerialNumber()),
                () -> Assertions.assertEquals(req.getPurchaseDate(), entity.getPurchaseDate()),
                () -> Assertions.assertEquals(req.getStatus(), entity.getStatus()),
                () -> Assertions.assertNull(entity.getLaboratory())
        );
    }
}