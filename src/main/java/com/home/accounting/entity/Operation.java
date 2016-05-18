package com.home.accounting.entity;

import org.joda.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /*@NotEmpty*/
    private double sum;


    private LocalDate date;

    /*@NotEmpty*/
    @Column(name = "flag_profit")
    private boolean flagProfit; /*profit - true, costs - false */

    @OneToOne
    @JoinColumn(name = "category_id", unique = false, nullable = false, updatable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)/*{CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}*/
    @JoinColumn(name = "account_id", nullable = false, unique = false/*, insertable = false*/)
    private Account account;

    public Operation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isFlagProfit() {
        return flagProfit;
    }

    public void setFlagProfit(boolean flagProfit) {
        this.flagProfit = flagProfit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    @Temporal(TemporalType.DATE)
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
