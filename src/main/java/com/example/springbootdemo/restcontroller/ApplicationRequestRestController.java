package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.model.ApplicationRequest;
import com.example.springbootdemo.service.ApplicationRequestService2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class ApplicationRequestRestController {

    private final ApplicationRequestService2 applicationRequestService;

    @GetMapping
    public ResponseEntity<List<ApplicationRequest>> getAllRequests() {
        return ResponseEntity.ok(applicationRequestService.getAllApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationRequest> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationRequestService.getApplicationById(id));
    }

    @PostMapping
    public ResponseEntity<ApplicationRequest> createRequest(@RequestBody ApplicationRequest request) {
        request.setHandled(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationRequestService.saveApplication(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationRequest> updateRequest(@PathVariable Long id,
                                            @RequestBody ApplicationRequest request) {
        return ResponseEntity.ok(applicationRequestService.updateApplication(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        applicationRequestService.deleteApplication(id);
        return ResponseEntity.ok().build();
    }
}