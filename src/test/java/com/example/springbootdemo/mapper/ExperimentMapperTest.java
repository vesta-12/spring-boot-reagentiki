package com.example.springbootdemo.mapper;

import com.example.springbootdemo.DTO.ExperimentRequestDTO;
import com.example.springbootdemo.DTO.ExperimentResponseDTO;
import com.example.springbootdemo.model.Laboratory;
import com.example.springbootdemo.model.Experiment;
import com.example.springbootdemo.model.Researcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ExperimentMapperTest {

    @Autowired
    private ExperimentMapper experimentMapper;

    @Test
    public void toDtoTest() {
        Laboratory lab = new Laboratory();
        lab.setId(1L);
        lab.setName("Lab");

        Researcher r1 = new Researcher();
        r1.setId(10L);
        r1.setFirstName("A");
        r1.setLastName("B");

        Researcher r2 = new Researcher();
        r2.setId(11L);
        r2.setFirstName("C");
        r2.setLastName("D");

        Experiment entity = new Experiment();
        entity.setId(100L);
        entity.setExperimentName("Exp");
        entity.setLeaderName("Leader");
        entity.setDate(LocalDate.now());
        entity.setLaboratory(lab);
        entity.setResearchers(List.of(r1, r2));

        ExperimentResponseDTO dto = experimentMapper.toDto(entity);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dto),
                () -> Assertions.assertNotNull(dto.getId()),
                () -> Assertions.assertNotNull(dto.getExperimentName()),
                () -> Assertions.assertNotNull(dto.getLeaderName()),
                () -> Assertions.assertNotNull(dto.getLaboratoryId()),
                () -> Assertions.assertNotNull(dto.getResearcherIds()),

                () -> Assertions.assertEquals(entity.getId(), dto.getId()),
                () -> Assertions.assertEquals(entity.getExperimentName(), dto.getExperimentName()),
                () -> Assertions.assertEquals(entity.getLeaderName(), dto.getLeaderName()),
                () -> Assertions.assertEquals(lab.getId(), dto.getLaboratoryId()),
                () -> Assertions.assertEquals(2, dto.getResearcherIds().size())
        );
    }

    @Test
    public void toEntityTest() {
        ExperimentRequestDTO req = new ExperimentRequestDTO();
        req.setExperimentName("Exp Create");
        req.setLeaderName("Leader Create");
        req.setDate(LocalDate.now());
        req.setMethod("M");
        req.setReagentsUsed("R");
        req.setResultSummary("S");

        Experiment entity = experimentMapper.toEntity(req);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(entity),
                () -> Assertions.assertNotNull(entity.getExperimentName()),
                () -> Assertions.assertNotNull(entity.getLeaderName()),
                () -> Assertions.assertNotNull(entity.getDate()),
                () -> Assertions.assertEquals(req.getExperimentName(), entity.getExperimentName()),
                () -> Assertions.assertEquals(req.getLeaderName(), entity.getLeaderName()),
                () -> Assertions.assertEquals(req.getMethod(), entity.getMethod())
        );
    }
}
