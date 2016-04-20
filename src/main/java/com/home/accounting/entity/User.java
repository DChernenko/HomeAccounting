package com.home.accounting.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "authorizations")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Min(value = 4)
    @Max(value = 10)
    @Column(name = "login")
    private String login;

    @NotEmpty
    @Min(value = 4)
    @Max(value = 10)
    private String password;

    @NotEmpty
    @Min(value = 4)
    @Max(value = 32)
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty
    @Min(value = 16)
    @Max(value = 125)
    @Column(length = 3)
    private int age;

    @NotEmpty
    @Email
    @Min(value = 10)
    @Max(value = 32)
    private String email;

    @OneToOne(optional = false)
    @JoinColumn(name = "account_id", unique = true, nullable = false, updatable = false)
    private Account account;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
