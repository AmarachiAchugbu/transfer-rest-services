package com.example.restservices.models;

import javax.persistence.Column;

public class Transfers {
    @Column(nullable = false)
    private long from;

    private long to;

    private double amount;

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
