package com.example.bank.view;

import com.example.bank.model.Account;

public class ConsoleView {
    public void showWelcome() {
        System.out.println("\n=== Simple Banking App ===");
    }
    public void showMenu() {
        System.out.println("\n1) Create Account");
        System.out.println("2) Deposit");
        System.out.println("3) Withdraw");
        System.out.println("4) Update Account Name");
        System.out.println("5) Retrieve Account");
        System.out.println("6) Delete Account");
        System.out.println("7) Exit");
        System.out.print("Choose (1-7): ");
    }
    public void showAccount(Account a) {
        System.out.println("\nAccount Details: " + a);
    }
    public void showSuccess(String msg) { System.out.println("SUCCESS: " + msg); }
    public void showError(String msg) { System.out.println("ERROR: " + msg); }
}
