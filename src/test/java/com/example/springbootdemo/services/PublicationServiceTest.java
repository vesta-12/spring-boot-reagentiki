package com.example.springbootdemo.services;

import com.example.springbootdemo.DTO.PublicationRequestDTO;
import com.example.springbootdemo.DTO.PublicationResponseDTO;
import com.example.springbootdemo.model.Experiment;
import com.example.springbootdemo.model.Publication;
import com.example.springbootdemo.repository.ExperimentRepository;
import com.example.springbootdemo.repository.PublicationRepository;
import com.example.springbootdemo.service.PublicationService;
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
public class PublicationServiceTest {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private ExperimentRepository experimentRepository;

    @BeforeEach
    public void cleanDatabase() {
        publicationRepository.deleteAll();
        experimentRepository.deleteAll();
    }

    @Test
    public void createPublicationTest() {
        Experiment exp = new Experiment();
        exp.setExperimentName("Exp Pub");
        exp.setLeaderName("Leader");
        exp.setDate(LocalDate.now());
        Experiment savedExp = experimentRepository.save(exp);

        PublicationRequestDTO req = new PublicationRequestDTO();
        req.setTitle("Test Publication");
        req.setJournal("Journal");
        req.setPublishedDate(LocalDate.of(2025, 12, 1));
        req.setDoi("10.1000/test.pub.001");
        req.setExperimentId(savedExp.getId());

        PublicationResponseDTO created = publicationService.createPublication(req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(created),
                () -> Assertions.assertNotNull(created.getId()),
                () -> Assertions.assertEquals(req.getTitle(), created.getTitle()),
                () -> Assertions.assertEquals(savedExp.getId(), created.getExperimentId())
        );

        Publication fromDb = publicationRepository.findById(created.getId()).orElse(null);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(fromDb),
                () -> Assertions.assertNotNull(fromDb.getExperiment()),
                () -> Assertions.assertEquals(savedExp.getId(), fromDb.getExperiment().getId())
        );
    }

    @Test
    public void getPublicationsByExperimentTest() {
        Experiment exp = new Experiment();
        exp.setExperimentName("Exp Filter");
        exp.setLeaderName("Leader");
        exp.setDate(LocalDate.now());
        Experiment savedExp = experimentRepository.save(exp);

        Publication p = new Publication();
        p.setTitle("Pub 1");
        p.setDoi("10.1000/test.pub.002");
        p.setPublishedDate(LocalDate.now());
        p.setExperiment(savedExp);
        publicationRepository.save(p);

        List<PublicationResponseDTO> list = publicationService.getPublicationsByExperiment(savedExp.getId());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(list),
                () -> Assertions.assertFalse(list.isEmpty()),
                () -> Assertions.assertEquals(savedExp.getId(), list.get(0).getExperimentId())
        );
    }

    @Test
    public void updatePublicationTest() {
        Experiment exp = new Experiment();
        exp.setExperimentName("Exp Update");
        exp.setLeaderName("Leader");
        exp.setDate(LocalDate.now());
        Experiment savedExp = experimentRepository.save(exp);

        Publication p = new Publication();
        p.setTitle("Old Title");
        p.setDoi("10.1000/test.pub.003");
        p.setExperiment(savedExp);
        Publication saved = publicationRepository.save(p);

        PublicationRequestDTO req = new PublicationRequestDTO();
        req.setTitle("New Title");
        req.setJournal("New Journal");
        req.setPublishedDate(LocalDate.of(2025, 11, 15));
        req.setDoi("10.1000/test.pub.003"); // тот же doi
        req.setExperimentId(savedExp.getId());

        PublicationResponseDTO updated = publicationService.updatePublication(saved.getId(), req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(updated),
                () -> Assertions.assertEquals(saved.getId(), updated.getId()),
                () -> Assertions.assertEquals("New Title", updated.getTitle())
        );
    }

    @Test
    public void deletePublicationTest() {
        Publication p = new Publication();
        p.setTitle("To Delete");
        p.setDoi("10.1000/test.pub.004");
        Publication saved = publicationRepository.save(p);

        publicationService.deletePublication(saved.getId());

        Assertions.assertTrue(publicationRepository.findById(saved.getId()).isEmpty());
    }
}