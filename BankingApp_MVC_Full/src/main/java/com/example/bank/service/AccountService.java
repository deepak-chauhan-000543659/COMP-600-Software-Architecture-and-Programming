package com.example.bank.service;

import com.example.bank.model.Account;
import java.math.BigDecimal;

public interface AccountService {
    void createAccount(String accountNumber, String customerName, BigDecimal initialDeposit);
    Account getAccount(String accountNumber);
    void updateAccount(String accountNumber, String newCustomerName);
    void deleteAccount(String accountNumber);
    void deposit(String accountNumber, BigDecimal amount);
    void withdraw(String accountNumber, BigDecimal amount);
}
