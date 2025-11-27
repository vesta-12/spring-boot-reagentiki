package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}