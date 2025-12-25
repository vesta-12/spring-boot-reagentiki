package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.PublicationRequestDTO;
import com.example.springbootdemo.DTO.PublicationResponseDTO;
import com.example.springbootdemo.model.Experiment;
import com.example.springbootdemo.model.Publication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class PublicationMapperTest {

    @Autowired
    private PublicationMapper publicationMapper;

    @Test
    public void toDtoTest() {
        Experiment exp = new Experiment();
        exp.setId(5L);

        Publication entity = new Publication();
        entity.setId(100L);
        entity.setTitle("PCR Optimization");
        entity.setJournal("Molecular Methods");
        entity.setPublishedDate(LocalDate.of(2025, 12, 1));
        entity.setDoi("10.1000/mm.2025.014");
        entity.setExperiment(exp);

        PublicationResponseDTO dto = publicationMapper.toDto(entity);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dto),
                () -> Assertions.assertEquals(entity.getId(), dto.getId()),
                () -> Assertions.assertEquals(entity.getTitle(), dto.getTitle()),
                () -> Assertions.assertEquals(entity.getJournal(), dto.getJournal()),
                () -> Assertions.assertEquals(entity.getPublishedDate(), dto.getPublishedDate()),
                () -> Assertions.assertEquals(entity.getDoi(), dto.getDoi()),
                () -> Assertions.assertEquals(exp.getId(), dto.getExperimentId())
        );
    }

    @Test
    public void toEntityTest() {
        PublicationRequestDTO req = new PublicationRequestDTO();
        req.setTitle("Acid-Base Titration Accuracy");
        req.setJournal("Journal of Chemical Education");
        req.setPublishedDate(LocalDate.of(2025, 11, 15));
        req.setDoi("10.1000/jce.2025.001");
        req.setExperimentId(1L);

        Publication entity = publicationMapper.toEntity(req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(entity),
                () -> Assertions.assertEquals(req.getTitle(), entity.getTitle()),
                () -> Assertions.assertEquals(req.getJournal(), entity.getJournal()),
                () -> Assertions.assertEquals(req.getPublishedDate(), entity.getPublishedDate()),
                () -> Assertions.assertEquals(req.getDoi(), entity.getDoi()),
                () -> Assertions.assertNull(entity.getExperiment())
        );
    }
}