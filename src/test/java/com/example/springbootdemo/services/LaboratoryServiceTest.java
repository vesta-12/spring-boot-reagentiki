package com.example.springbootdemo.services;

import com.example.springbootdemo.DTO.LaboratoryRequestDTO;
import com.example.springbootdemo.DTO.LaboratoryResponseDTO;
import com.example.springbootdemo.model.Equipment;
import com.example.springbootdemo.model.Experiment;
import com.example.springbootdemo.model.Laboratory;
import com.example.springbootdemo.model.Publication;
import com.example.springbootdemo.repository.EquipmentRepository;
import com.example.springbootdemo.repository.ExperimentRepository;
import com.example.springbootdemo.repository.LaboratoryRepository;
import com.example.springbootdemo.repository.PublicationRepository;
import com.example.springbootdemo.service.LaboratoryService;
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
public class LaboratoryServiceTest {

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private ExperimentRepository experimentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @BeforeEach
    public void cleanDatabase() {
        // publications -> experiments -> equipment -> laboratories
        publicationRepository.deleteAll();
        experimentRepository.deleteAll();
        equipmentRepository.deleteAll();
        laboratoryRepository.deleteAll();
    }

    @Test
    public void getAllLaboratoriesTest() {
        Laboratory lab = new Laboratory();
        lab.setName("List Lab");
        lab.setLocation("Almaty");
        lab.setDepartment("Chemistry");
        lab.setEquipmentDescription("Desc");
        laboratoryRepository.save(lab);

        List<LaboratoryResponseDTO> labs = laboratoryService.getAllLaboratories();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(labs),
                () -> Assertions.assertFalse(labs.isEmpty())
        );
    }

    @Test
    public void getLaboratoryByIdTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Get Lab");
        lab.setLocation("Astana");
        lab.setDepartment("Bio");
        lab.setEquipmentDescription("Desc");
        Laboratory saved = laboratoryRepository.save(lab);

        LaboratoryResponseDTO dto = laboratoryService.getLaboratoryById(saved.getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dto),
                () -> Assertions.assertEquals(saved.getId(), dto.getId()),
                () -> Assertions.assertEquals(saved.getName(), dto.getName()),
                () -> Assertions.assertEquals(saved.getLocation(), dto.getLocation()),
                () -> Assertions.assertEquals(saved.getDepartment(), dto.getDepartment())
        );
    }

    @Test
    public void createLaboratoryTest() {
        LaboratoryRequestDTO req = new LaboratoryRequestDTO();
        req.setName("Created Lab");
        req.setLocation("Karaganda");
        req.setDepartment("Materials");
        req.setEquipmentDescription("Equipment Desc");

        LaboratoryResponseDTO created = laboratoryService.createLaboratory(req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(created),
                () -> Assertions.assertNotNull(created.getId()),
                () -> Assertions.assertEquals(req.getName(), created.getName())
        );

        Laboratory fromDb = laboratoryRepository.findById(created.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertEquals(req.getName(), fromDb.getName())
        );
    }

    @Test
    public void updateLaboratoryTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Old Lab");
        lab.setLocation("Old City");
        lab.setDepartment("Old Dept");
        lab.setEquipmentDescription("Old Desc");
        Laboratory saved = laboratoryRepository.save(lab);

        LaboratoryRequestDTO req = new LaboratoryRequestDTO();
        req.setName("Updated Lab");
        req.setLocation("New City");
        req.setDepartment("New Dept");
        req.setEquipmentDescription("New Desc");

        LaboratoryResponseDTO updated = laboratoryService.updateLaboratory(saved.getId(), req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(updated),
                () -> Assertions.assertEquals(saved.getId(), updated.getId()),
                () -> Assertions.assertEquals("Updated Lab", updated.getName()),
                () -> Assertions.assertEquals("New City", updated.getLocation())
        );

        Laboratory fromDb = laboratoryRepository.findById(saved.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertEquals("Updated Lab", fromDb.getName()),
                () -> Assertions.assertEquals("New City", fromDb.getLocation())
        );
    }

    @Test
    public void deleteLaboratoryTest() {
        Laboratory lab = new Laboratory();
        lab.setName("ToDelete Lab");
        lab.setLocation("City");
        lab.setDepartment("Dept");
        lab.setEquipmentDescription("Desc");
        Laboratory saved = laboratoryRepository.save(lab);

        laboratoryService.deleteLaboratory(saved.getId());

        Assertions.assertTrue(laboratoryRepository.findById(saved.getId()).isEmpty());
    }

    @Test
    public void oneToManyExperimentRelationTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Relation Lab");
        lab.setLocation("Almaty");
        lab.setDepartment("Chemistry");
        lab.setEquipmentDescription("Desc");
        Laboratory savedLab = laboratoryRepository.save(lab);

        Experiment exp = new Experiment();
        exp.setExperimentName("Relation Exp");
        exp.setLeaderName("Leader");
        exp.setDate(LocalDate.now());
        exp.setLaboratory(savedLab);
        experimentRepository.save(exp);

        List<Experiment> fromDb = experimentRepository.findByLaboratoryId(savedLab.getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertFalse(fromDb.isEmpty()),
                () -> Assertions.assertEquals(savedLab.getId(), fromDb.get(0).getLaboratory().getId())
        );
    }

    @Test
    public void manyToOneEquipmentRelationTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Equip Lab");
        lab.setLocation("Astana");
        lab.setDepartment("Bio");
        lab.setEquipmentDescription("Desc");
        Laboratory savedLab = laboratoryRepository.save(lab);

        Equipment eq = new Equipment();
        eq.setName("PCR Thermal Cycler");
        eq.setSerialNumber("PCR-TEST-0001");
        eq.setStatus("ACTIVE");
        eq.setPurchaseDate(LocalDate.of(2022, 2, 20));
        eq.setLaboratory(savedLab);
        equipmentRepository.save(eq);

        List<Equipment> list = equipmentRepository.findByLaboratoryId(savedLab.getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(list),
                () -> Assertions.assertFalse(list.isEmpty()),
                () -> Assertions.assertEquals(savedLab.getId(), list.get(0).getLaboratory().getId())
        );
    }
}
