package com.ouahib.bankAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestBankAccountApplication {

	public static void main(String[] args) {
		SpringApplication.from(BankAccountApplication::main).with(TestBankAccountApplication.class).run(args);
	}

}
