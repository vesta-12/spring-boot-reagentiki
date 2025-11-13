package com.example.springbootdemo.service;

import com.example.springbootdemo.model.Courses;
import com.example.springbootdemo.repository.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesService {

    private final CoursesRepository coursesRepository;

    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    public Courses getCourseById(Long id) {
        return coursesRepository.findById(id).orElse(null);
    }

    public Courses createCourse(Courses course) {
        return coursesRepository.save(course);
    }

    public Courses updateCourse(Long id, Courses courseDetails) {
        Courses course = getCourseById(id);
        if (course != null) {
            course.setName(courseDetails.getName());
            course.setDescription(courseDetails.getDescription());
            course.setPrice(courseDetails.getPrice());
            return coursesRepository.save(course);
        }
        return null;
    }

    public void deleteCourse(Long id) {
        coursesRepository.deleteById(id);
    }
}