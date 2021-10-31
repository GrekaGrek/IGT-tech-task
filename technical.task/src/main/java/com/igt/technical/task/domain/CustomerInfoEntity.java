package com.igt.technical.task.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(mappedBy = "customerInfo", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<CustomerDebtCaseEntity> customerDebtCases = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public CustomerInfoEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CustomerInfoEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CustomerInfoEntity setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CustomerInfoEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerInfoEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CustomerInfoEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public List<CustomerDebtCaseEntity> getCustomerDebtCases() {
        return customerDebtCases;
    }

    public CustomerInfoEntity setCustomerDebtCases(List<CustomerDebtCaseEntity> customerDebtCases) {
        this.customerDebtCases = customerDebtCases;
        return this;
    }
}
