package com.home.accounting.service.impl;

import com.home.accounting.entity.Operation;
import com.home.accounting.repository.OperationRepository;
import com.home.accounting.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public void addOperation(Operation operation) {
        operationRepository.saveAndFlush(operation);
    }

    @Override
    public void editOperation(Operation operation) {
        operationRepository.saveAndFlush(operation);
    }

    @Override
    public void delete(long id) {
        operationRepository.delete(id);
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Operation getIdOperation(long id) {
        return operationRepository.findOne(id);
    }

    /*@Override
    public List<Operation> getAccountOperations(Account account) {
        return operationRepository.fi;
    }*/


}
