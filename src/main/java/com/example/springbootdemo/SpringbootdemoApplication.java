package com.example.springbootdemo;
import com.example.springbootdemo.model.Courses;
import com.example.springbootdemo.model.Operators;
import com.example.springbootdemo.repository.CoursesRepository;
import com.example.springbootdemo.repository.OperatorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringbootdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(CoursesRepository coursesRepository,
                                        OperatorsRepository operatorsRepository) {
        return args -> {
            if (coursesRepository.count() == 0) {
                List<Courses> courses = List.of(
                        new Courses(null, "Linguistics fundamentals", "Learn you language constructions", 150000, new ArrayList<>()),
                        new Courses(null, "Ballroom", "You will dance like Zagoruychenko", 350000, new ArrayList<>()),
                        new Courses(null, "Biology for med universities", "Special program for young doctors", 250000, new ArrayList<>())
                );
                coursesRepository.saveAll(courses);
            }
            if (operatorsRepository.count() == 0) {
                List<Operators> operators = List.of(
                        new Operators(null, "Adel", "Abdrakhmanova", "Dance", new ArrayList<>()),
                        new Operators(null, "Darya", "Ivaikina", "Natural sciences", new ArrayList<>()),
                        new Operators(null, "Evgeniy", "Kurlikov", "Linguist", new ArrayList<>())
                );
                operatorsRepository.saveAll(operators);
            }
        };
    }
}