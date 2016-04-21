package com.home.accounting.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /* @NotEmpty
     @Min(value = 4)
     @Max(value = 10)*/
    private String name;

    @OneToOne(mappedBy = "category")
    private Operation operation;


    public Category() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
