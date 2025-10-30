package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.Operators;
import com.example.springbootdemo.repository.OperatorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
@RequiredArgsConstructor
public class OperatorsRestController {

    private final OperatorsRepository operatorsRepository;

    @GetMapping
    public List<Operators> getAllOperators() {
        return operatorsRepository.findAll();
    }

    @PostMapping
    public Operators createOperator(@RequestBody Operators operator) {
        return operatorsRepository.save(operator);
    }

    @PutMapping("/{operatorId}/assign/{requestId}")
    public long assignOperatorToRequest(
            @PathVariable Long operatorId,
            @PathVariable Long requestId) {

        return applicationRequestService.assignSingleOperator(requestId, operatorId);
    }

    @DeleteMapping("/{id}")
    public void deleteOperator(@PathVariable Long id) {
        operatorsRepository.deleteById(id);
    }
}