package com.home.accounting.entity;

import javax.persistence.*;

@Entity
@Table(name = "authorizations")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*@NotEmpty*/
    @Column(name = "login")
    private String login;

    /*@NotEmpty*/
    private String password;

    /*@NotEmpty*/
    @Column(name = "full_name")
    private String fullName;

    /*@NotEmpty
    @Max(150)
    @Min(16)
    @Size(min = 1, max = 200)*/
    private Integer age;

    /*@NotEmpty
    @Email*/
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", unique = true, nullable = true, updatable = true)
    private Account account;

    public User() {
    }

    public Long getId() {
        return id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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
