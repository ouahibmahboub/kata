package com.ouahib.bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AccountControllerSeleniumTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // We need to set the path of our chrome driver
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testSaveMoney() {
        // Open the save money form
        driver.get("http://localhost:8080/saveMoney");

        // Fill out the form with valid data
        driver.findElement(By.id("accountNumber")).sendKeys("1234567890");
        driver.findElement(By.id("amount")).sendKeys("100.0");

        // Submit the form
        driver.findElement(By.id("submitButton")).click();

        // Verify the successful message is displayed
        assert driver.getTitle().equals("Account Management");
        assert driver.findElement(By.id("confirmationMessage")).getText().contains("Save money successful!");
    }

    @Test
    public void testRetrieveMoney() {
        // Open the retrieve money form
        driver.get("http://localhost:8080/withdrawal");

        // Fill out the form with valid data
        driver.findElement(By.id("accountNumber")).sendKeys("1234567890");
        driver.findElement(By.id("amount")).sendKeys("50.0");

        // Submit the form
        driver.findElement(By.id("submitButton")).click();

        // Verify the successful message is displayed
        assert driver.getTitle().equals("Account Management");
        assert driver.findElement(By.id("confirmationMessage")).getText().contains("Withdrawal successful!");
    }

    @Test
    public void testRetrieveMoneyWithInsufficientFunds() {
        // Open the retrieve money form
        driver.get("http://localhost:8080/withdrawal");

        // Fill out the form with invalid data (amount greater than balance)
        driver.findElement(By.id("accountNumber")).sendKeys("1234567890");
        driver.findElement(By.id("amount")).sendKeys("150.0");

        // Submit the form
        driver.findElement(By.id("submitButton")).click();

        // Verify the error message is displayed
        assert driver.getTitle().equals("Account Management");
        assert driver.findElement(By.id("error")).getText().contains("Insufficient funds in account 1234567890");
    }
}
