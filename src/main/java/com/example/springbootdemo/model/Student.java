package com.example.springbootdemo.model;
import lombok.*;

@Data
public class Student {
    private Long id;
    private String name;
    private String surname;
    private String mark;
    private int exam;

    public Student(Long id, String name, String surname, int exam) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.exam = exam;
        this.mark = defineMark(exam);
    }

    private String defineMark(int exam) {
        if (exam >= 90) return "A";
        else if (exam >= 75) return "B";
        else if (exam >= 60) return "C";
        else if (exam >= 50) return "D";
        else return "F";
    }
}