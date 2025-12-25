package com.example.springbootdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationResponseDTO {
    private Long id;
    private String title;
    private String journal;
    private LocalDate publishedDate;
    private String doi;
    private Long experimentId;
}
