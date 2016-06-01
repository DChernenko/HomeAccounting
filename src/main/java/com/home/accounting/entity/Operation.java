package com.home.accounting.entity;

import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;

@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /*@NotEmpty*/
    private double sum;

    @Null
    private LocalDate date;

    /*@NotEmpty*/
    @Column(name = "flag_profit")
    private boolean flagProfit; /*profit - true, costs - false */

    @ManyToMany/*(cascade = CascadeType.ALL)*/(fetch = FetchType.EAGER)
    @JoinTable(name = "operations_cagegories",
            joinColumns = @JoinColumn(name = "operation_id"/*, referencedColumnName = "id"*/),
            inverseJoinColumns = @JoinColumn(name = "category_id"/*, referencedColumnName = "id"*/))
    private List<Category> categories;


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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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
