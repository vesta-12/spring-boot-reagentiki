package com.example.springbootdemo.services;

import com.example.springbootdemo.DTO.ResearcherRequestDTO;
import com.example.springbootdemo.DTO.ResearcherResponseDTO;
import com.example.springbootdemo.model.Researcher;
import com.example.springbootdemo.repository.ResearcherRepository;
import com.example.springbootdemo.service.ResearcherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ResearcherServiceTest {

    @Autowired
    private ResearcherService researcherService;

    @Autowired
    private ResearcherRepository researcherRepository;

    @BeforeEach
    public void cleanDatabase() {
        researcherRepository.deleteAll();
    }

    @Test
    public void getAllResearchersTest() {
        Researcher r = new Researcher();
        r.setFirstName("Aruzhan");
        r.setLastName("Nurzhanova");
        r.setEmail("aruzhan.list@uni.kz");
        r.setSpecialization("Chemistry");
        r.setPhoneNumber("+7-701-111-22-33");
        researcherRepository.save(r);

        List<ResearcherResponseDTO> list = researcherService.getAllResearchers();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(list),
                () -> Assertions.assertFalse(list.isEmpty())
        );
    }

    @Test
    public void getResearcherByIdTest() {
        Researcher r = new Researcher();
        r.setFirstName("Dias");
        r.setLastName("Sadykov");
        r.setEmail("dias.get@uni.kz");
        r.setSpecialization("Biochemistry");
        r.setPhoneNumber("+7-702-222-33-44");
        Researcher saved = researcherRepository.save(r);

        ResearcherResponseDTO dto = researcherService.getResearcherById(saved.getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dto),
                () -> Assertions.assertEquals(saved.getId(), dto.getId()),
                () -> Assertions.assertEquals(saved.getFirstName(), dto.getFirstName()),
                () -> Assertions.assertEquals(saved.getLastName(), dto.getLastName()),
                () -> Assertions.assertEquals(saved.getEmail(), dto.getEmail())
        );
    }

    @Test
    public void createResearcherTest() {
        ResearcherRequestDTO req = new ResearcherRequestDTO();
        req.setFirstName("Madina");
        req.setLastName("Kairatova");
        req.setEmail("madina.create@uni.kz");
        req.setSpecialization("Materials");
        req.setPhoneNumber("+7-705-333-44-55");

        ResearcherResponseDTO created = researcherService.createResearcher(req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(created),
                () -> Assertions.assertNotNull(created.getId()),
                () -> Assertions.assertEquals(req.getEmail(), created.getEmail())
        );

        Researcher fromDb = researcherRepository.findById(created.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertEquals(req.getEmail(), fromDb.getEmail())
        );
    }

    @Test
    public void updateResearcherTest() {
        Researcher r = new Researcher();
        r.setFirstName("Old");
        r.setLastName("Name");
        r.setEmail("old.update@uni.kz");
        r.setSpecialization("OldSpec");
        r.setPhoneNumber("123");
        Researcher saved = researcherRepository.save(r);

        ResearcherRequestDTO req = new ResearcherRequestDTO();
        req.setFirstName("New");
        req.setLastName("Name");
        req.setEmail("new.update@uni.kz");
        req.setSpecialization("NewSpec");
        req.setPhoneNumber("999");

        ResearcherResponseDTO updated = researcherService.updateResearcher(saved.getId(), req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(updated),
                () -> Assertions.assertEquals(saved.getId(), updated.getId()),
                () -> Assertions.assertEquals("New", updated.getFirstName()),
                () -> Assertions.assertEquals("new.update@uni.kz", updated.getEmail())
        );

        Researcher fromDb = researcherRepository.findById(saved.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertEquals("New", fromDb.getFirstName()),
                () -> Assertions.assertEquals("new.update@uni.kz", fromDb.getEmail())
        );
    }

    @Test
    public void deleteResearcherTest() {
        Researcher r = new Researcher();
        r.setFirstName("To");
        r.setLastName("Delete");
        r.setEmail("delete@uni.kz");
        r.setSpecialization("Spec");
        r.setPhoneNumber("000");
        Researcher saved = researcherRepository.save(r);

        researcherService.deleteResearcher(saved.getId());

        Assertions.assertTrue(researcherRepository.findById(saved.getId()).isEmpty());
    }
}