package com.Vassah.MyBank.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.AccountStatus;
import com.Vassah.MyBank.Model.Transaction;
import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Services.UserManager;

@Controller
public class AccountController {

    @Autowired
    private UserManager userManager;

    @GetMapping("/admin")
    public String Admin() {
        return "admin";
    }

    @GetMapping("/user/profile")
    public String profile(@AuthenticationPrincipal User user, Model model)
    {
        model.addAttribute("user", user);
        var accounts = user.getAccounts();
        List<Account> deposit = new ArrayList<Account>();
        List<Account> credit = new ArrayList<Account>();
        List<Account> debit = new ArrayList<Account>();
        List<Transaction> transactions = new ArrayList<Transaction>();
        if (accounts != null)
        {
            for (Account acc : accounts)
            {
                transactions.addAll(acc.getTransactions());
                switch (acc.getStatus())
                {
                    case Debit : debit.add(acc); break;
                    case Credit: credit.add(acc); break;
                    case Deposit: deposit.add(acc); break;
                }
            }
        }
        transactions.sort((Transaction tr1, Transaction tr2) -> tr1.getProccesTime().compareTo(tr2.getProccesTime()));
        model.addAttribute("transactions", transactions);
        model.addAttribute("debit", debit);
        model.addAttribute("credit", credit);
        model.addAttribute("deposit", deposit);
        return "profile";
    }

}
