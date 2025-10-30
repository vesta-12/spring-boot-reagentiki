package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.Courses;
import com.example.springbootdemo.repository.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CoursesRestController {

    private final CoursesRepository coursesRepository;

    @GetMapping
    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    @PostMapping
    public Courses createCourse(@RequestBody Courses course) {
        return coursesRepository.save(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        coursesRepository.deleteById(id);
    }
}