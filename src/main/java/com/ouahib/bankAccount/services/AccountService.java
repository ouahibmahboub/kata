package com.ouahib.bankAccount.services;

import com.ouahib.bankAccount.entities.Account;
import com.ouahib.bankAccount.entities.Transaction;

import java.util.List;

public interface AccountService {

    void saveMoney(String accountNumber, double amount);

    double retrieveMoney(String accountNumber, double amount);

    List<Transaction> checkOperation(String accountNumber);

    Account getAccount(String accountNumber);
}