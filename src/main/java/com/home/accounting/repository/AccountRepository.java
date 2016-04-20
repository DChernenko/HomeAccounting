package com.home.accounting.repository;

import com.home.accounting.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AccountRepository  extends JpaRepository<Account,Long>{
}
