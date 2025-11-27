package com.example.bank.controller;

import com.example.bank.dao.AccountDAOImpl;
import com.example.bank.exception.BusinessException;
import com.example.bank.model.Account;
import com.example.bank.service.AccountService;
import com.example.bank.service.AccountServiceImpl;
import com.example.bank.view.ConsoleView;

import java.math.BigDecimal;
import java.util.Scanner;

public class AppController {
    private final ConsoleView view = new ConsoleView();
    private final AccountService service = new AccountServiceImpl(new AccountDAOImpl());

    public void run() {
        view.showWelcome();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            try {
                view.showMenu();
                String choice = sc.nextLine();
                switch (choice) {
                    case "1": create(sc); break;
                    case "2": deposit(sc); break;
                    case "3": withdraw(sc); break;
                    case "4": update(sc); break;
                    case "5": retrieve(sc); break;
                    case "6": delete(sc); break;
                    case "7": running = false; break;
                    default: view.showError("Invalid choice. Try again.");
                }
            } catch (BusinessException | IllegalArgumentException ex) {
                view.showError(ex.getMessage());
            } catch (Exception ex) {
                view.showError("Unexpected error: " + ex.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }

    private void create(Scanner sc) {
        System.out.print("Enter new account number: ");
        String accNo = sc.nextLine().trim();
        System.out.print("Enter customer name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter initial deposit (e.g., 100.00): ");
        BigDecimal amount = new BigDecimal(sc.nextLine().trim());
        service.createAccount(accNo, name, amount);
        view.showSuccess("Account created.");
    }

    private void deposit(Scanner sc) {
        System.out.print("Account number: ");
        String accNo = sc.nextLine().trim();
        System.out.print("Amount to deposit: ");
        BigDecimal amount = new BigDecimal(sc.nextLine().trim());
        service.deposit(accNo, amount);
        view.showSuccess("Deposit successful.");
    }

    private void withdraw(Scanner sc) {
        System.out.print("Account number: ");
        String accNo = sc.nextLine().trim();
        System.out.print("Amount to withdraw: ");
        BigDecimal amount = new BigDecimal(sc.nextLine().trim());
        service.withdraw(accNo, amount);
        view.showSuccess("Withdrawal successful.");
    }

    private void update(Scanner sc) {
        System.out.print("Account number: ");
        String accNo = sc.nextLine().trim();
        System.out.print("New customer name: ");
        String name = sc.nextLine().trim();
        service.updateAccount(accNo, name);
        view.showSuccess("Account updated.");
    }

    private void retrieve(Scanner sc) {
        System.out.print("Account number: ");
        String accNo = sc.nextLine().trim();
        Account a = service.getAccount(accNo);
        view.showAccount(a);
    }

    private void delete(Scanner sc) {
        System.out.print("Account number: ");
        String accNo = sc.nextLine().trim();
        service.deleteAccount(accNo);
        view.showSuccess("Account deleted.");
    }
}
