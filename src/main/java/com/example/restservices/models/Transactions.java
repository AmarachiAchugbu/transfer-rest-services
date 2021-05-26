package com.example.restservices.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;
import java.util.Date;

@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reference", unique = true)
    private UUID reference = UUID.randomUUID();

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "account_number", nullable = false)
    private long accountNumber;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt = new Date();

    public Long getId() {
        return id;
    }

    public UUID getReference() {
        return reference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
