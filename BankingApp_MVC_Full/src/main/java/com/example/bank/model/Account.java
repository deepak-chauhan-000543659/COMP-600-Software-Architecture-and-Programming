package com.example.bank.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private Long id; // DB PK
    private String accountNumber; // unique
    private String customerName;
    private BigDecimal balance;

    public Account() {}

    public Account(String accountNumber, String customerName, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance == null ? BigDecimal.ZERO : balance;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    @Override public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account a = (Account) o;
        return Objects.equals(accountNumber, a.accountNumber);
    }

    @Override public int hashCode() { return Objects.hash(accountNumber); }
}
