package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByExperimentId(Long experimentId);
}

