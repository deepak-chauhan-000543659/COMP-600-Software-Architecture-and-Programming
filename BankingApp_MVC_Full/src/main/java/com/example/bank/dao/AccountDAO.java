package com.example.bank.dao;

import com.example.bank.model.Account;
import java.math.BigDecimal;
import java.util.Optional;

public interface AccountDAO {
    void create(Account account);
    Optional<Account> findByAccountNumber(String accountNumber);
    void update(Account account);
    void deleteByAccountNumber(String accountNumber);
    void updateBalance(String accountNumber, BigDecimal newBalance);
}
