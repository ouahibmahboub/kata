package com.ouahib.bankAccount;

import com.ouahib.bankAccount.dtos.RetrieveMoneyRequest;
import com.ouahib.bankAccount.dtos.SaveMoneyRequest;
import com.ouahib.bankAccount.entities.Account;
import com.ouahib.bankAccount.exceptions.InsufficientFundsException;
import com.ouahib.bankAccount.services.AccountService;
import com.ouahib.bankAccount.services.AccountServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class AccountServiceTests {
    private static AccountService service;
    private static Account account;

    @BeforeAll
    public static void setUp() {
        service = new AccountServiceImpl();
        account = new Account("123456789");
        service.saveMoney(account.getAccountNumber(), 100.0);
    }

    @AfterAll
    public static void tearDown() {
        service = null;
        account = null;
    }

    @Test
    public void saveMoneyShouldIncreaseBalance() {
        SaveMoneyRequest request = new SaveMoneyRequest("123456789", 50.0);

        service.saveMoney(request.getAccountNumber(), request.getAmount());
        Account retrievedAccount = service.getAccount(request.getAccountNumber());

        assertEquals(150.0, retrievedAccount.getBalance(), 0.001);
    }

    @Test
    public void retrieveMoneyShouldDecreaseBalance() throws InsufficientFundsException {
        RetrieveMoneyRequest request = new RetrieveMoneyRequest("123456789", 50.0);

        double withdrawnAmount = service.retrieveMoney(request.getAccountNumber(), request.getAmount());

        assertEquals(50.0, withdrawnAmount);
        Account retrievedAccount = service.getAccount(request.getAccountNumber());

        assertEquals(100.0, retrievedAccount.getBalance(), 0.001);
    }

    @Test
    public void retrieveMoneyWithInsufficientFundsShouldThrowException() {
        RetrieveMoneyRequest request = new RetrieveMoneyRequest("123456789", 200.0);

        assertThrows(InsufficientFundsException.class, () ->
                service.retrieveMoney(request.getAccountNumber(), request.getAmount())
        );
    }
}