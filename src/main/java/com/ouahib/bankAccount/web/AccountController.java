package com.ouahib.bankAccount.web;

import com.ouahib.bankAccount.dtos.RetrieveMoneyRequest;
import com.ouahib.bankAccount.dtos.SaveMoneyRequest;
import com.ouahib.bankAccount.entities.Transaction;
import com.ouahib.bankAccount.exceptions.TransactionAdvice;
import com.ouahib.bankAccount.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {


    private AccountService accountService;
    private TransactionAdvice transactionAdvice;

    public AccountController(AccountService accountService, TransactionAdvice transactionAdvice) {
        this.accountService = accountService;
        this.transactionAdvice = transactionAdvice;
    }

    //method save money
    @PostMapping("/saveMoney")
    public void saveMoney(@RequestBody SaveMoneyRequest request) {
        accountService.saveMoney(request.getAccountNumber(), request.getAmount());
    }
    //method retrieve money
    @PostMapping("/retrieveMoney")
    public double retrieveMoney(@RequestBody RetrieveMoneyRequest request) {
        return accountService.retrieveMoney(request.getAccountNumber(), request.getAmount());
    }
    //method check my operations
    @GetMapping("/statement/{accountNumber}")
    public List<Transaction> checkOperation(@PathVariable String accountNumber) {
        return accountService.checkOperation(accountNumber);
    }
}