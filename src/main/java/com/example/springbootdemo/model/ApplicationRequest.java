package com.example.springbootdemo.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "application_request")
public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "commentary", nullable = false)
    private String commentary;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "handled", nullable = false)
    private boolean handled;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course;

    @ManyToMany
    @JoinTable(
            name = "request_operator",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "operator_id")
    )
    private List<Operators> operators = new ArrayList<>();
}