package com.example.springbootdemo.service;
import com.example.springbootdemo.model.ApplicationRequest;
import com.example.springbootdemo.model.Courses;
import com.example.springbootdemo.model.Operators;
import com.example.springbootdemo.repository.ApplicationRequestRepository;
import com.example.springbootdemo.repository.CoursesRepository;
import com.example.springbootdemo.repository.OperatorsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationRequestService {

    private final ApplicationRequestRepository applicationRequestRepository;
    private final CoursesRepository coursesRepository;
    private final OperatorsRepository operatorsRepository;

    public List<ApplicationRequest> getAllApplications() {
        return applicationRequestRepository.findAll();
    }

    public ApplicationRequest getApplicationById(Long id) {
        return applicationRequestRepository.findById(id).orElse(null);
    }

    public List<ApplicationRequest> getPendingApplications() {
        return applicationRequestRepository.findByHandledFalse();
    }

    public List<ApplicationRequest> getProcessedApplications() {
        return applicationRequestRepository.findByHandledTrue();
    }

    public void addApplication(ApplicationRequest application, Long courseId) {
        Courses course = coursesRepository.findById(courseId).orElse(null);
        if (course != null) {
            application.setCourse(course);
            application.setHandled(false);
            applicationRequestRepository.save(application);
        }
    }

    @Transactional
    public void assignOperators(Long requestId, List<Long> operatorIds) {
        ApplicationRequest request = getApplicationById(requestId);
        if (request != null && !request.isHandled()) {
            List<Operators> operators = operatorsRepository.findAllById(operatorIds);
            request.getOperators().clear();
            request.getOperators().addAll(operators);
            request.setHandled(!operators.isEmpty());
            applicationRequestRepository.save(request);
        }
    }

    @Transactional
    public void removeOperator(Long requestId, Long operatorId) {
        ApplicationRequest request = getApplicationById(requestId);
        if (request != null) {
            request.getOperators().removeIf(operator -> operator.getId().equals(operatorId));
            if (request.getOperators().isEmpty()) {
                request.setHandled(false);
            }
            applicationRequestRepository.save(request);
        }
    }
    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }
    public List<Operators> getAllOperators() {
        return operatorsRepository.findAll();
    }
    public void deleteApplication(Long id) {
        if (applicationRequestRepository.existsById(id)) {
            applicationRequestRepository.deleteById(id);
        }
    }
}