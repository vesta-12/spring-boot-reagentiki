package com.example.springbootdemo.controller;
import com.example.springbootdemo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentikiController {
    private List<Student> studentsList = new ArrayList<>();
    public StudentikiController() {
        studentsList.add(new Student(1L, "Andrei", "Linenko", 45));
        studentsList.add(new Student(2L, "Darya", "Ivaikina", 93));
        studentsList.add(new Student(3L, "Yevgeniy", "Kurlikov", 95));
        studentsList.add(new Student(4L, "Adel", "Abdrakhmanova", 93));
        studentsList.add(new Student(5L, "Leana", "Alieva", 56));
        studentsList.add(new Student(6L, "Milana", "Sotnikova", 78));
        studentsList.add(new Student(7L, "Ivan", "Opnenko", 43));
        studentsList.add(new Student(8L, "Kirill", "Banov", 37));
    }
    private Long nextId = 9L;
    @GetMapping("/lab3-students")
    public String lab3StudentsPage(Model model) {
        model.addAttribute("studentsList", studentsList);
        return "students";
    }
    @PostMapping("/lab3-students/add")
    public String addStudent(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int exam) {

        Student newStudent = new Student(nextId++, name, surname, exam);
        studentsList.add(newStudent);
        return "redirect:/lab3-students";
    }
}
