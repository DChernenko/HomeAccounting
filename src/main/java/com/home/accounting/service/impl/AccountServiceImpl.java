package com.home.accounting.service.impl;

import com.home.accounting.model.Account;
import com.home.accounting.model.Operation;
import com.home.accounting.model.User;
import com.home.accounting.repository.AccountRepository;
import com.home.accounting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;



    @Override
    public void addAccount(Operation operation) {

    }

    @Override
    public void deleteAccount(long id) {

    }

    @Override
    public void editAccount(Account account) {

    }

    @Override
    public void getBalance(User user) {

    }
}
