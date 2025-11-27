package com.example.bank.dao;

import com.example.bank.exception.DataAccessException;
import com.example.bank.model.Account;
import com.example.bank.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public void create(Account a) {
        final String sql = "INSERT INTO accounts (account_number, customer_name, balance) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getAccountNumber());
            ps.setString(2, a.getCustomerName());
            ps.setBigDecimal(3, a.getBalance());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) a.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error creating account", e);
        }
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        final String sql = "SELECT id, account_number, customer_name, balance FROM accounts WHERE account_number=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account a = new Account();
                    a.setId(rs.getLong("id"));
                    a.setAccountNumber(rs.getString("account_number"));
                    a.setCustomerName(rs.getString("customer_name"));
                    a.setBalance(rs.getBigDecimal("balance"));
                    return Optional.of(a);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving account", e);
        }
    }

    @Override
    public void update(Account a) {
        final String sql = "UPDATE accounts SET customer_name=?, balance=? WHERE account_number=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getCustomerName());
            ps.setBigDecimal(2, a.getBalance());
            ps.setString(3, a.getAccountNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error updating account", e);
        }
    }

    @Override
    public void deleteByAccountNumber(String accountNumber) {
        final String sql = "DELETE FROM accounts WHERE account_number=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error deleting account", e);
        }
    }

    @Override
    public void updateBalance(String accountNumber, BigDecimal newBalance) {
        final String sql = "UPDATE accounts SET balance=? WHERE account_number=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, newBalance);
            ps.setString(2, accountNumber);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error updating balance", e);
        }
    }
}
