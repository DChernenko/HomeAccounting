package com.home.accounting.repository;

import com.home.accounting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u from User u where u.login=:name")
    User findUserByName(@Param("name") String name);
}
