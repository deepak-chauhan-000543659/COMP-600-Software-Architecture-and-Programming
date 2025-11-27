package com.example.bank.service;

import com.example.bank.dao.AccountDAO;
import com.example.bank.exception.BusinessException;
import com.example.bank.model.Account;
import com.example.bank.util.DBUtil;
import com.example.bank.util.ValidationUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void createAccount(String accountNumber, String customerName, BigDecimal initialDeposit) {
        ValidationUtil.requireNonBlank(accountNumber, "Account Number");
        ValidationUtil.requireNonBlank(customerName, "Customer Name");
        ValidationUtil.requireNonNegative(initialDeposit, "Initial Deposit");

        accountDAO.findByAccountNumber(accountNumber).ifPresent(a -> {
            throw new BusinessException("Account number already exists.");
        });

        Account a = new Account(accountNumber, customerName, initialDeposit);
        accountDAO.create(a);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountDAO.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BusinessException("Account not found: " + accountNumber));
    }

    @Override
    public void updateAccount(String accountNumber, String newCustomerName) {
        ValidationUtil.requireNonBlank(newCustomerName, "Customer Name");
        Account existing = getAccount(accountNumber);
        existing.setCustomerName(newCustomerName);
        accountDAO.update(existing);
    }

    @Override
    public void deleteAccount(String accountNumber) {
        // ensure exists for clearer messaging
        getAccount(accountNumber);
        accountDAO.deleteByAccountNumber(accountNumber);
    }

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {
        ValidationUtil.requirePositive(amount, "Deposit amount");
        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);
            Account acc = getAccount(accountNumber);
            accountDAO.updateBalance(accountNumber, acc.getBalance().add(amount));
            conn.commit();
        } catch (SQLException e) {
            throw new BusinessException("Deposit failed due to DB error.", e);
        }
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {
        ValidationUtil.requirePositive(amount, "Withdrawal amount");
        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);
            Account acc = getAccount(accountNumber);
            if (acc.getBalance().compareTo(amount) < 0) {
                throw new BusinessException("Insufficient funds.");
            }
            accountDAO.updateBalance(accountNumber, acc.getBalance().subtract(amount));
            conn.commit();
        } catch (SQLException e) {
            throw new BusinessException("Withdrawal failed due to DB error.", e);
        }
    }
}
