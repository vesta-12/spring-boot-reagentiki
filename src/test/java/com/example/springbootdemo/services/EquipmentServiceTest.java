package com.example.springbootdemo.services;

import com.example.springbootdemo.DTO.EquipmentRequestDTO;
import com.example.springbootdemo.DTO.EquipmentResponseDTO;
import com.example.springbootdemo.model.Equipment;
import com.example.springbootdemo.model.Laboratory;
import com.example.springbootdemo.repository.EquipmentRepository;
import com.example.springbootdemo.repository.LaboratoryRepository;
import com.example.springbootdemo.service.EquipmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class EquipmentServiceTest {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @BeforeEach
    public void cleanDatabase() {
        equipmentRepository.deleteAll();
        laboratoryRepository.deleteAll();
    }

    @Test
    public void createEquipmentTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Equip Lab");
        lab.setLocation("Astana");
        lab.setDepartment("Bio");
        lab.setEquipmentDescription("Desc");
        Laboratory savedLab = laboratoryRepository.save(lab);

        EquipmentRequestDTO req = new EquipmentRequestDTO();
        req.setName("Thermal Cycler");
        req.setSerialNumber("SER-TEST-0001");
        req.setPurchaseDate(LocalDate.of(2022, 2, 20));
        req.setStatus("ACTIVE");
        req.setLaboratoryId(savedLab.getId());

        EquipmentResponseDTO created = equipmentService.createEquipment(req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(created),
                () -> Assertions.assertNotNull(created.getId()),
                () -> Assertions.assertEquals(req.getName(), created.getName()),
                () -> Assertions.assertEquals(savedLab.getId(), created.getLaboratoryId())
        );

        Equipment fromDb = equipmentRepository.findById(created.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertNotNull(fromDb.getLaboratory()),
                () -> Assertions.assertEquals(savedLab.getId(), fromDb.getLaboratory().getId())
        );
    }

    @Test
    public void getEquipmentByLaboratoryTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Filter Lab");
        lab.setLocation("Almaty");
        lab.setDepartment("Chem");
        lab.setEquipmentDescription("Desc");
        Laboratory savedLab = laboratoryRepository.save(lab);

        Equipment eq = new Equipment();
        eq.setName("Balance");
        eq.setSerialNumber("SER-TEST-0002");
        eq.setStatus("ACTIVE");
        eq.setPurchaseDate(LocalDate.of(2023, 9, 12));
        eq.setLaboratory(savedLab);
        equipmentRepository.save(eq);

        List<EquipmentResponseDTO> list = equipmentService.getEquipmentByLaboratory(savedLab.getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(list),
                () -> Assertions.assertFalse(list.isEmpty()),
                () -> Assertions.assertEquals(savedLab.getId(), list.get(0).getLaboratoryId())
        );
    }

    @Test
    public void updateEquipmentTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Update Lab");
        lab.setLocation("Almaty");
        lab.setDepartment("Chem");
        lab.setEquipmentDescription("Desc");
        Laboratory savedLab = laboratoryRepository.save(lab);

        Equipment eq = new Equipment();
        eq.setName("Old Name");
        eq.setSerialNumber("SER-TEST-0003");
        eq.setStatus("MAINTENANCE");
        eq.setPurchaseDate(LocalDate.of(2021, 6, 5));
        eq.setLaboratory(savedLab);
        Equipment savedEq = equipmentRepository.save(eq);

        EquipmentRequestDTO req = new EquipmentRequestDTO();
        req.setName("New Name");
        req.setSerialNumber("SER-TEST-0003");
        req.setStatus("ACTIVE");
        req.setPurchaseDate(LocalDate.of(2021, 6, 5));
        req.setLaboratoryId(savedLab.getId());

        EquipmentResponseDTO updated = equipmentService.updateEquipment(savedEq.getId(), req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(updated),
                () -> Assertions.assertEquals(savedEq.getId(), updated.getId()),
                () -> Assertions.assertEquals("New Name", updated.getName()),
                () -> Assertions.assertEquals("ACTIVE", updated.getStatus())
        );
    }

    @Test
    public void deleteEquipmentTest() {
        Equipment eq = new Equipment();
        eq.setName("To Delete");
        eq.setSerialNumber("SER-TEST-0004");
        Equipment saved = equipmentRepository.save(eq);

        equipmentService.deleteEquipment(saved.getId());

        Assertions.assertTrue(equipmentRepository.findById(saved.getId()).isEmpty());
    }
}