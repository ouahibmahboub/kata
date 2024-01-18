package com.ouahib.bankAccount.services;

import com.ouahib.bankAccount.entities.Account;
import com.ouahib.bankAccount.entities.Transaction;
import com.ouahib.bankAccount.exceptions.InsufficientFundsException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private Map<String, Account> accounts = new HashMap<>();

    @Override
    public void saveMoney(String accountNumber, double amount) {
        LOGGER.info("Saving money to account {} with amount {}", accountNumber, amount);

        Account account = accounts.get(accountNumber);
        if (account == null) {
            account = new Account(accountNumber);
            accounts.put(accountNumber, account);
        }

        account.deposit(amount);
        LOGGER.info("Successfully saved money to account {}", accountNumber);
    }

    @Override
    public double retrieveMoney(String accountNumber, double amount) throws InsufficientFundsException {
        LOGGER.info("Retrieving money from account number: {} with amount {}", accountNumber, amount);

        Account account = accounts.get(accountNumber);
        if (account == null) {
            LOGGER.error("Account not found for account number: {}", accountNumber);
            return -1; // Account not found
        }

        if (account.getBalance() < amount) {
            LOGGER.error("Not enough funds in account {}", accountNumber);
            throw new InsufficientFundsException("Not enough funds in account " + accountNumber);
        }

        double withdrawnAmount = account.withdraw(amount);
        accounts.put(accountNumber, account);
        LOGGER.info("Successfully retrieved money from account {}", accountNumber);
        return withdrawnAmount;
    }
    @Override
    public Account getAccount(String accountNumber) {
        LOGGER.info("Retrieving account for account number: {}", accountNumber);

        Account account = accounts.get(accountNumber);
        if (account == null) {
            LOGGER.error("Account not found for account number: {}", accountNumber);
            return null;
        }

        return account;
    }
    @Override
    public List<Transaction> checkOperation(String accountNumber) {
        LOGGER.info("Retrieving transaction history for account {}", accountNumber);

        if (!accounts.containsKey(accountNumber)) {
            LOGGER.error("Account {} not found", accountNumber);
            return null;
        } else {
            return accounts.get(accountNumber).getTransactions();
        }
    }
}