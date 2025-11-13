package com.example.springbootdemo.service;

import com.example.springbootdemo.model.Operators;
import com.example.springbootdemo.repository.OperatorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorsService {

    private final OperatorsRepository operatorsRepository;

    public List<Operators> getAllOperators() {
        return operatorsRepository.findAll();
    }

    public Operators getOperatorById(Long id) {
        return operatorsRepository.findById(id).orElse(null);
    }

    public Operators createOperator(Operators operator) {
        return operatorsRepository.save(operator);
    }

    public Operators updateOperator(Long id, Operators operatorDetails) {
        Operators operator = getOperatorById(id);
        if (operator != null) {
            operator.setName(operatorDetails.getName());
            operator.setSurname(operatorDetails.getSurname());
            operator.setDepartment(operatorDetails.getDepartment());
            return operatorsRepository.save(operator);
        }
        return null;
    }

    public void deleteOperator(Long id) {
        operatorsRepository.deleteById(id);
    }
}