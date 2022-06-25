package com.Vassah.MyBank.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String profile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        var accounts = user.getAccounts();
        List<Account> deposit = new ArrayList<Account>();
        List<Account> credit = new ArrayList<Account>();
        List<Account> debit = new ArrayList<Account>();
        List<Transaction> transactions = new ArrayList<Transaction>();
        if (accounts != null) {
            for (Account acc : accounts) {
                transactions.addAll(acc.getTransactions());
                switch (acc.getStatus()) {
                    case Debit:
                        debit.add(acc);
                        break;
                    case Credit:
                        credit.add(acc);
                        break;
                    case Deposit:
                        deposit.add(acc);
                        break;
                }
            }
        }
        transactions.sort((Transaction tr1, Transaction tr2) -> tr1.getProccesTime().compareTo(tr2.getProccesTime()));
        model.addAttribute("transactions", transactions);
        model.addAttribute("debit", debit);
        model.addAttribute("credit", credit);
        model.addAttribute("deposit", deposit);
        return "user/profile";
    }

    @GetMapping("/user/newaccount")
    public String newAccount(@RequestParam(required = false, name = "card") String card, Model model) {
        if (card == null) {
            return "user/newaccount";
        }
       switch(card)
       {
        case "debit": return "user/success";
        case "deposit": return "user/success";
        case "credit": return "user/success";
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
    public String Self(Model model) {
        return "user/self";
    }

}
