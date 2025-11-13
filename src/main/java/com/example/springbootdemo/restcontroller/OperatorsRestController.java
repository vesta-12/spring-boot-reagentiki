package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.model.ApplicationRequest;
import com.example.springbootdemo.model.Operators;
import com.example.springbootdemo.service.ApplicationRequestService2;
import com.example.springbootdemo.service.OperatorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
@RequiredArgsConstructor
public class OperatorsRestController {

    private final OperatorsService operatorsService;
    private final ApplicationRequestService2 applicationRequestService;

    @GetMapping
    public ResponseEntity<List<Operators>> getAllOperators() {
        return ResponseEntity.ok(operatorsService.getAllOperators());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operators> getOperatorById(@PathVariable Long id) {
        Operators operator = operatorsService.getOperatorById(id);
        return operator != null ? ResponseEntity.ok(operator) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Operators> createOperator(@RequestBody Operators operator) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(operatorsService.createOperator(operator));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operators> updateOperator(@PathVariable Long id, @RequestBody Operators operatorDetails) {
        Operators updatedOperator = operatorsService.updateOperator(id, operatorDetails);
        return updatedOperator != null ? ResponseEntity.ok(updatedOperator) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{operatorId}/assign/{requestId}")
    public ResponseEntity<ApplicationRequest> assignOperatorToRequest(
            @PathVariable Long operatorId,
            @PathVariable Long requestId) {

        ApplicationRequest result = applicationRequestService.assignSingleOperator(requestId, operatorId);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperator(@PathVariable Long id) {
        operatorsService.deleteOperator(id);
        return ResponseEntity.ok().build();
    }
}