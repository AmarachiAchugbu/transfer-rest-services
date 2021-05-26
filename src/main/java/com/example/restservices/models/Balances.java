package com.example.restservices.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "balances")
@EntityListeners(AuditingEntityListener.class)
public class Balances {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "my_sequence")
    @TableGenerator(name = "accountGen", initialValue = 10000, allocationSize = 100)
    @Column(name = "account_number", nullable = false)
    private long accountNumber;

    @Column(name = "balance", nullable = false)
    private double balance;

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
