package com.home.accounting.service.impl;

import com.home.accounting.entity.Account;
import com.home.accounting.entity.Operation;
import com.home.accounting.entity.User;
import com.home.accounting.repository.AccountRepository;
import com.home.accounting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public void addAccount(Account account) {
        accountRepository.saveAndFlush(account);
    }

    @Override
    public void deleteAccount(long id) {
        accountRepository.delete(id);
    }

    @Override
    public void editAccount(Account account) {
        accountRepository.saveAndFlush(account);
    }

    @Override
    public void getBalance(User user) {
        accountRepository.findOne(user.getAccount().getId());
    }
}
