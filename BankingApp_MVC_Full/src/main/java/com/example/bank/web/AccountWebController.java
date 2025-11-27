package com.example.bank.web;

import com.example.bank.dao.AccountDAOImpl;
import com.example.bank.exception.BusinessException;
import com.example.bank.model.Account;
import com.example.bank.service.AccountService;
import com.example.bank.service.AccountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class AccountWebController {

    // Reuse your existing layers
    private final AccountService service = new AccountServiceImpl(new AccountDAOImpl());

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Banking Web UI");
        return "home";
    }

    @GetMapping("/accounts/new")
    public String showCreateForm() {
        return "create-account";
    }

    @PostMapping("/accounts")
    public String createAccount(@RequestParam String accountNumber,
                                @RequestParam String customerName,
                                @RequestParam BigDecimal initialDeposit,
                                Model model) {
        try {
            service.createAccount(accountNumber.trim(), customerName.trim(), initialDeposit);
            model.addAttribute("success", "Account created successfully.");
        } catch (BusinessException | IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "create-account";
    }

    @GetMapping("/accounts/find")
    public String showFindForm() {
        return "find-account";
    }

    @PostMapping("/accounts/find")
    public String findAccount(@RequestParam String accountNumber, Model model) {
        try {
            Account acc = service.getAccount(accountNumber.trim());
            model.addAttribute("account", acc);
        } catch (BusinessException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "find-account";
    }

    @GetMapping("/accounts/deposit")
    public String showDepositForm() {
        return "deposit";
    }

    @PostMapping("/accounts/deposit")
    public String doDeposit(@RequestParam String accountNumber,
                            @RequestParam BigDecimal amount,
                            Model model) {
        try {
            service.deposit(accountNumber.trim(), amount);
            model.addAttribute("success", "Deposit successful.");
        } catch (BusinessException | IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "deposit";
    }

    @GetMapping("/accounts/withdraw")
    public String showWithdrawForm() {
        return "withdraw";
    }

    @PostMapping("/accounts/withdraw")
    public String doWithdraw(@RequestParam String accountNumber,
                             @RequestParam BigDecimal amount,
                             Model model) {
            try {
                service.withdraw(accountNumber.trim(), amount);
                model.addAttribute("success", "Withdrawal successful.");
            } catch (BusinessException | IllegalArgumentException ex) {
                model.addAttribute("error", ex.getMessage());
            }
            return "withdraw";
    }

    @GetMapping("/accounts/update")
    public String showUpdateForm() {
        return "update-account";
    }

    @PostMapping("/accounts/update")
    public String doUpdate(@RequestParam String accountNumber,
                           @RequestParam String customerName,
                           Model model) {
        try {
            service.updateAccount(accountNumber.trim(), customerName.trim());
            model.addAttribute("success", "Account updated successfully.");
        } catch (BusinessException | IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "update-account";
    }

    @GetMapping("/accounts/delete")
    public String showDeleteForm() {
        return "delete-account";
    }

    @PostMapping("/accounts/delete")
    public String doDelete(@RequestParam String accountNumber, Model model) {
        try {
            service.deleteAccount(accountNumber.trim());
            model.addAttribute("success", "Account deleted successfully.");
        } catch (BusinessException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "delete-account";
    }
}
