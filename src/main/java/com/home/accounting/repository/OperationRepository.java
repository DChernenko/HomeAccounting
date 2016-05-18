package com.home.accounting.repository;

import com.home.accounting.entity.Account;
import com.home.accounting.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("Select o from Operation o where o.account=:account")
    List<Operation> findAccountOperation(@Param("account") Account account);
}
