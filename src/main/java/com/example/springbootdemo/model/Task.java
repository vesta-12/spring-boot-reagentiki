package com.example.springbootdemo.model;
import lombok.*;

@Data
@AllArgsConstructor
public class Task {
    private Long id;
    private String name;
    private String deadlineDate;
    private boolean isCompleted;
}