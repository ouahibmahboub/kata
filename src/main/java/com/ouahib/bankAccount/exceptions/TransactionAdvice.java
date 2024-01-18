package com.ouahib.bankAccount.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TransactionAdvice {

    @ExceptionHandler({InsufficientFundsException.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleError(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        if (ex instanceof InsufficientFundsException) {
            errorResponse.setDescription("Insufficient funds in account.");
        } else if (ex instanceof RuntimeException) {
            errorResponse.setDescription("Internal server error.");
        }

        return errorResponse;
    }
}