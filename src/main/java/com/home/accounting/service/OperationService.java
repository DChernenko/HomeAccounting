package com.home.accounting.service;

import com.home.accounting.model.Operation;

import java.util.List;

public interface OperationService {

    void addOperation(Operation operation);

    void editOperation(Operation operation);

/*    void delete(Operation operation);*/

    void delete(long id);

    /*void delete(long[] id);*/

    List<Operation> getAllOperations();

    Operation getIdOperation(long id);

   /* List<Operation> getAccountOperations(Account account);*/
}
