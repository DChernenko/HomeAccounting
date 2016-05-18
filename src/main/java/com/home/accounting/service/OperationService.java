package com.home.accounting.service;

import com.home.accounting.entity.Account;
import com.home.accounting.entity.Operation;

import java.util.List;

public interface OperationService {

    void addOperation(Operation operation);

    void editOperation(Operation operation);

/*    void deleteOperation(Operation operation);*/

    void deleteOperation(long id);

    /*void deleteOperation(long[] id);*/

    List<Operation> getAllOperations();

    Operation findOperation(long id);

    List<Operation> findAccountOperations(Account account);
}
