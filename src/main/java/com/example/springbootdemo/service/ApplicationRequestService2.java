package com.example.springbootdemo.service;

import com.example.springbootdemo.model.ApplicationRequest;
import com.example.springbootdemo.model.Operators;
import com.example.springbootdemo.repository.ApplicationRequestRepository;
import com.example.springbootdemo.repository.OperatorsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationRequestService2 {

    private final ApplicationRequestRepository applicationRequestRepository;
    private final OperatorsRepository operatorsRepository;

    public List<ApplicationRequest> getAllApplications() {
        return applicationRequestRepository.findAll();
    }

    public ApplicationRequest getApplicationById(Long id) {
        return applicationRequestRepository.findById(id).orElse(null);
    }

    public ApplicationRequest saveApplication(ApplicationRequest application) {
        application.setHandled(false);
        return applicationRequestRepository.save(application);
    }

    @Transactional
    public ApplicationRequest updateApplication(Long id, ApplicationRequest requestDetails) {
        ApplicationRequest request = getApplicationById(id);
        if (request != null) {
            request.setUserName(requestDetails.getUserName());
            request.setCommentary(requestDetails.getCommentary());
            request.setPhone(requestDetails.getPhone());
            request.setHandled(requestDetails.isHandled());
            return applicationRequestRepository.save(request);
        }
        return null;
    }

    public void deleteApplication(Long id) {
        if (applicationRequestRepository.existsById(id)) {
            applicationRequestRepository.deleteById(id);
        }
    }

    @Transactional
    public ApplicationRequest assignSingleOperator(Long requestId, Long operatorId) {
        ApplicationRequest request = getApplicationById(requestId);
        Operators operator = operatorsRepository.findById(operatorId).orElse(null);

        if (request != null && operator != null) {
            request.getOperators().clear();
            request.getOperators().add(operator);
            return applicationRequestRepository.save(request);
        }
        return null;
    }
}