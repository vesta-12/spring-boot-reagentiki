package com.example.springbootdemo.services;

import com.example.springbootdemo.DTO.ExperimentRequestDTO;
import com.example.springbootdemo.DTO.ExperimentResponseDTO;
import com.example.springbootdemo.model.Experiment;
import com.example.springbootdemo.model.Laboratory;
import com.example.springbootdemo.model.Publication;
import com.example.springbootdemo.model.Researcher;
import com.example.springbootdemo.repository.ExperimentRepository;
import com.example.springbootdemo.repository.LaboratoryRepository;
import com.example.springbootdemo.repository.PublicationRepository;
import com.example.springbootdemo.repository.ResearcherRepository;
import com.example.springbootdemo.service.ExperimentService;
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
public class ExperimentServiceTest {

    @Autowired
    private ExperimentService experimentService;

    @Autowired
    private ExperimentRepository experimentRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private ResearcherRepository researcherRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @BeforeEach
    public void cleanDatabase() {
        publicationRepository.deleteAll();
        experimentRepository.deleteAll();
        researcherRepository.deleteAll();
        laboratoryRepository.deleteAll();
    }

    @Test
    public void getAllExperimentsTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Lab A");
        lab.setLocation("Almaty");
        lab.setDepartment("Chemistry");
        lab.setEquipmentDescription("Basic equipment");
        Laboratory savedLab = laboratoryRepository.save(lab);

        Experiment exp = new Experiment();
        exp.setExperimentName("Exp 1");
        exp.setLeaderName("Leader 1");
        exp.setDate(LocalDate.now());
        exp.setLaboratory(savedLab);
        experimentRepository.save(exp);

        List<ExperimentResponseDTO> list = experimentService.getAllExperiments();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(list),
                () -> Assertions.assertFalse(list.isEmpty())
        );
    }

    @Test
    public void getExperimentByIdTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Lab B");
        lab.setLocation("Astana");
        lab.setDepartment("Bio");
        lab.setEquipmentDescription("PCR, microscopes");
        Laboratory savedLab = laboratoryRepository.save(lab);

        Experiment exp = new Experiment();
        exp.setExperimentName("PCR Test");
        exp.setLeaderName("Dias Sadykov");
        exp.setDate(LocalDate.of(2025, 11, 3));
        exp.setLaboratory(savedLab);
        Experiment savedExp = experimentRepository.save(exp);

        ExperimentResponseDTO dto = experimentService.getExperimentById(savedExp.getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dto),
                () -> Assertions.assertEquals(savedExp.getId(), dto.getId()),
                () -> Assertions.assertEquals(savedExp.getExperimentName(), dto.getExperimentName()),
                () -> Assertions.assertEquals(savedExp.getLeaderName(), dto.getLeaderName()),
                () -> Assertions.assertEquals(savedLab.getId(), dto.getLaboratoryId())
        );
    }

    @Test
    public void createExperimentTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Lab Create");
        lab.setLocation("Karaganda");
        lab.setDepartment("Materials");
        lab.setEquipmentDescription("Hardness tester");
        Laboratory savedLab = laboratoryRepository.save(lab);

        Researcher r1 = new Researcher();
        r1.setFirstName("Aruzhan");
        r1.setLastName("Nurzhanova");
        r1.setEmail("aruzhan.test@uni.kz");
        r1.setSpecialization("Chemistry");
        r1.setPhoneNumber("+7-701-111-22-33");
        Researcher savedR1 = researcherRepository.save(r1);

        Researcher r2 = new Researcher();
        r2.setFirstName("Timur");
        r2.setLastName("Akhmetov");
        r2.setEmail("timur.test@uni.kz");
        r2.setSpecialization("Synthesis");
        r2.setPhoneNumber("+7-707-444-55-66");
        Researcher savedR2 = researcherRepository.save(r2);

        ExperimentRequestDTO req = new ExperimentRequestDTO();
        req.setExperimentName("Created Experiment");
        req.setLeaderName("Leader Created");
        req.setDate(LocalDate.now());
        req.setMethod("Method A");
        req.setReagentsUsed("Reagents A");
        req.setResultSummary("Summary A");
        req.setLaboratoryId(savedLab.getId());
        req.setResearcherIds(List.of(savedR1.getId(), savedR2.getId()));

        ExperimentResponseDTO created = experimentService.createExperiment(req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(created),
                () -> Assertions.assertNotNull(created.getId()),
                () -> Assertions.assertEquals(req.getExperimentName(), created.getExperimentName()),
                () -> Assertions.assertEquals(req.getLeaderName(), created.getLeaderName()),
                () -> Assertions.assertEquals(savedLab.getId(), created.getLaboratoryId()),
                () -> Assertions.assertNotNull(created.getResearcherIds()),
                () -> Assertions.assertEquals(2, created.getResearcherIds().size())
        );

        Experiment fromDb = experimentRepository.findByIdWithResearchers(created.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertEquals(req.getExperimentName(), fromDb.getExperimentName()),
                () -> Assertions.assertEquals(req.getLeaderName(), fromDb.getLeaderName()),
                () -> Assertions.assertNotNull(fromDb.getLaboratory()),
                () -> Assertions.assertEquals(savedLab.getId(), fromDb.getLaboratory().getId()),
                () -> Assertions.assertNotNull(fromDb.getResearchers()),
                () -> Assertions.assertEquals(2, fromDb.getResearchers().size())
        );
    }

    @Test
    public void updateExperimentTest() {
        Laboratory lab1 = new Laboratory();
        lab1.setName("Lab 1");
        lab1.setLocation("Almaty");
        lab1.setDepartment("Chemistry");
        lab1.setEquipmentDescription("Desc 1");
        Laboratory savedLab1 = laboratoryRepository.save(lab1);

        Laboratory lab2 = new Laboratory();
        lab2.setName("Lab 2");
        lab2.setLocation("Astana");
        lab2.setDepartment("Biotech");
        lab2.setEquipmentDescription("Desc 2");
        Laboratory savedLab2 = laboratoryRepository.save(lab2);

        Experiment exp = new Experiment();
        exp.setExperimentName("Old Experiment");
        exp.setLeaderName("Old Leader");
        exp.setDate(LocalDate.of(2025, 10, 10));
        exp.setLaboratory(savedLab1);
        Experiment savedExp = experimentRepository.save(exp);

        ExperimentRequestDTO req = new ExperimentRequestDTO();
        req.setExperimentName("Updated Experiment");
        req.setLeaderName("Updated Leader");
        req.setDate(LocalDate.of(2025, 12, 1));
        req.setMethod("Updated Method");
        req.setReagentsUsed("Updated Reagents");
        req.setResultSummary("Updated Summary");
        req.setLaboratoryId(savedLab2.getId());
        req.setResearcherIds(List.of());

        ExperimentResponseDTO updated = experimentService.updateExperiment(savedExp.getId(), req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(updated),
                () -> Assertions.assertEquals(savedExp.getId(), updated.getId()),
                () -> Assertions.assertEquals("Updated Experiment", updated.getExperimentName()),
                () -> Assertions.assertEquals("Updated Leader", updated.getLeaderName()),
                () -> Assertions.assertEquals(savedLab2.getId(), updated.getLaboratoryId())
        );

        Experiment fromDb = experimentRepository.findById(savedExp.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertEquals("Updated Experiment", fromDb.getExperimentName()),
                () -> Assertions.assertEquals("Updated Leader", fromDb.getLeaderName()),
                () -> Assertions.assertNotNull(fromDb.getLaboratory()),
                () -> Assertions.assertEquals(savedLab2.getId(), fromDb.getLaboratory().getId())
        );
    }

    @Test
    public void assignLaboratoryToExperimentTest() {
        Laboratory lab = new Laboratory();
        lab.setName("Lab Assign");
        lab.setLocation("Almaty");
        lab.setDepartment("Chemistry");
        lab.setEquipmentDescription("Equipment");
        Laboratory savedLab = laboratoryRepository.save(lab);

        Experiment exp = new Experiment();
        exp.setExperimentName("No Lab Yet");
        exp.setLeaderName("Leader");
        exp.setDate(LocalDate.now());
        Experiment savedExp = experimentRepository.save(exp);

        ExperimentResponseDTO updated = experimentService.assignLaboratoryToExperiment(savedExp.getId(), savedLab.getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(updated),
                () -> Assertions.assertEquals(savedLab.getId(), updated.getLaboratoryId())
        );
    }

    @Test
    public void manyToManyAddRemoveResearcherTest() {
        Researcher r = new Researcher();
        r.setFirstName("Madina");
        r.setLastName("Kairatova");
        r.setEmail("madina.test@uni.kz");
        r.setSpecialization("Materials");
        r.setPhoneNumber("+7-705-333-44-55");
        Researcher savedR = researcherRepository.save(r);

        Experiment exp = new Experiment();
        exp.setExperimentName("M2M Exp");
        exp.setLeaderName("Leader M2M");
        exp.setDate(LocalDate.now());
        Experiment savedExp = experimentRepository.save(exp);

        ExperimentResponseDTO afterAdd = experimentService.addResearcherToExperiment(savedExp.getId(), savedR.getId());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(afterAdd),
                () -> Assertions.assertNotNull(afterAdd.getResearcherIds()),
                () -> Assertions.assertTrue(afterAdd.getResearcherIds().contains(savedR.getId()))
        );

        ExperimentResponseDTO afterRemove = experimentService.removeResearcherFromExperiment(savedExp.getId(), savedR.getId());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(afterRemove),
                () -> Assertions.assertNotNull(afterRemove.getResearcherIds()),
                () -> Assertions.assertFalse(afterRemove.getResearcherIds().contains(savedR.getId()))
        );
    }

    @Test
    public void deleteExperimentTest() {
        Experiment exp = new Experiment();
        exp.setExperimentName("To Delete");
        exp.setLeaderName("Leader Delete");
        exp.setDate(LocalDate.now());
        Experiment savedExp = experimentRepository.save(exp);

        experimentService.deleteExperiment(savedExp.getId());

        Assertions.assertTrue(experimentRepository.findById(savedExp.getId()).isEmpty());
    }
}