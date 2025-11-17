package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearcherRepository extends JpaRepository<Researcher, Long> {
}