package com.home.accounting.service;

import com.home.accounting.entity.Account;
import com.home.accounting.entity.Operation;
import com.home.accounting.entity.User;

public interface AccountService {

    void addAccount(Operation operation);

    void deleteAccount(long id);

    void editAccount(Account account);

    void getBalance(User user);
}
