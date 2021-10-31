package com.igt.technical.task.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer_debt_cases")
public class CustomerDebtCaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private CustomerInfoEntity customerInfo;

    public Long getId() {
        return id;
    }

    public CustomerDebtCaseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public CustomerDebtCaseEntity setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public CustomerDebtCaseEntity setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public CustomerDebtCaseEntity setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public CustomerInfoEntity getCustomerInfo() {
        return customerInfo;
    }

    public CustomerDebtCaseEntity setCustomerInfo(CustomerInfoEntity customerInfo) {
        this.customerInfo = customerInfo;
        return this;
    }
}
