package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.ApplicationRequest;
import com.example.springbootdemo.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class ApplicationRequestRestController {

    private final ApplicationRequestService applicationRequestService;

    @GetMapping
    public List<ApplicationRequest> getAllRequests() {
        return applicationRequestService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationRequest getRequestById(@PathVariable Long id) {
        return applicationRequestService.getApplicationById(id);
    }

    @PostMapping
    public ApplicationRequest createRequest(@RequestBody ApplicationRequest request) {
        request.setHandled(false);
        return applicationRequestService.saveApplication(request);
    }

    @PutMapping("/{id}")
    public ApplicationRequest updateRequest(@PathVariable Long id,
                                            @RequestBody ApplicationRequest request) {
        return applicationRequestService.updateApplication(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        applicationRequestService.deleteApplication(id);
    }
}