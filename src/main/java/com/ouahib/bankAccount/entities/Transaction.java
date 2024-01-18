package com.ouahib.bankAccount.entities;

import com.ouahib.bankAccount.enums.TransactionType;
import lombok.*;

public class Transaction {

    private TransactionType type;
    private double amount;

    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}