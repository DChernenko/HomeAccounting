package com.home.accounting.repository;

import com.home.accounting.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AccountRepository  extends JpaRepository<Account,Long>{
}
