package com.Vassah.MyBank.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.Transaction;
import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Model.AccToAccTransfer;
import com.Vassah.MyBank.Services.AccountManager;
import com.Vassah.MyBank.Services.MoneySender;
import com.Vassah.MyBank.Services.UserManager;

@PreAuthorize("hasAuthority('User_role')")
@Controller
public class AccountController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AccountManager accManager;

    @Autowired
    private MoneySender moneySender;


    @GetMapping("/user/profile")
    public String profile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        var accounts = accManager.findAccountsByUser(user);
        List<Account> deposits = new ArrayList<Account>();
        List<Account> credits = new ArrayList<Account>();
        List<Account> debits = new ArrayList<Account>();
        List<Transaction> transactions = new ArrayList<Transaction>();
        if (accounts != null) {
            for (Account acc : accounts) {
                transactions.addAll(acc.getTransactions() == null? new ArrayList<Transaction>() : acc.getTransactions());
                switch (acc.getStatus()) {
                    case Debit:
                        debits.add(acc);
                        break;
                    case Credit:
                        credits.add(acc);
                        break;
                    case Deposit:
                        deposits.add(acc);
                        break;
                }
            }
        }
        transactions.sort((Transaction tr1, Transaction tr2) -> tr1.getProccesTime().compareTo(tr2.getProccesTime()));
        model.addAttribute("transactions", transactions);
        model.addAttribute("debit", debits);
        model.addAttribute("credit", credits);
        model.addAttribute("deposit", deposits);
        return "user/profile";
    }

    @GetMapping("/user/newaccount")
    public String newAccount(@RequestParam(required = false, name = "card") String card, @AuthenticationPrincipal User user, Model model) {
        if (card == null) {
            return "user/newaccount";
        }
       switch(card)
       {
        case "debit": 
            accManager.debit(user, "1234");
            return "user/success";
        case "deposit": 
            accManager.deposit(user, "1234");
            return "user/success";
        case "credit": 
            accManager.credit(user, "1234");
            return "user/success";
        default: return "user/newaccount";
       }
    }

    @GetMapping("/user/byphone")
    public String byPhone(Model model) {
        return "user/byphone";
    }

    @GetMapping("/user/bycard")
    public String byCard(Model model) {
        return "user/bycard";
    }

    @GetMapping("/user/self")
    public String Self(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("transferForm", new AccToAccTransfer());
        model.addAttribute("accounts", user.getAccounts());
        model.addAttribute("user", user);
        return "user/self";
    }

    @PostMapping("user/self")
    public String Self(@ModelAttribute AccToAccTransfer form, @AuthenticationPrincipal User user, Model model)
    {
        moneySender.SendMoney(form);
        return "redirect:/user/profile";
    }

}
