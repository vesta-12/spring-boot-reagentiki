package com.example.springbootdemo.repository;
import com.example.springbootdemo.model.Operators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorsRepository extends JpaRepository<Operators, Long> {
}