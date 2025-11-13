package com.example.springbootdemo.repository;
import com.example.springbootdemo.model.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
    List<ApplicationRequest> findByHandledFalse();
    List<ApplicationRequest> findByHandledTrue();
    List<ApplicationRequest> findByHandled(boolean handled);
}
