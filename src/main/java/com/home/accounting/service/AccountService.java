package com.home.accounting.service;

import com.home.accounting.model.Account;
import com.home.accounting.model.Operation;
import com.home.accounting.model.User;

public interface AccountService {

    void addAccount(Operation operation);

    void deleteAccount(long id);

    void editAccount(Account account);

    void getBalance(User user);
}
