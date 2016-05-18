package com.home.accounting.service;

import com.home.accounting.entity.Account;
import com.home.accounting.entity.User;

public interface AccountService {

    void addAccount(Account account);

    void deleteAccount(long id);

    void editAccount(Account account);

    Account getBalance(User user);
}
