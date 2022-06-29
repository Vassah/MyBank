package com.vassah.my_bank.сontrollers;

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

import com.vassah.my_bank.model.AccToAccTransfer;
import com.vassah.my_bank.model.AccToCardTransfer;
import com.vassah.my_bank.model.AccToPhoneTransfer;
import com.vassah.my_bank.model.Account;
import com.vassah.my_bank.model.AccountStatus;
import com.vassah.my_bank.model.Transaction;
import com.vassah.my_bank.model.User;
import com.vassah.my_bank.services.AccountManager;
import com.vassah.my_bank.services.MoneySender;

@PreAuthorize("hasAuthority('User_role')")
@Controller
public class AccountController {

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
                transactions.addAll(acc.getTransactions());
                switch (acc.getStatus()) {
                    case DEBIT:
                        debits.add(acc);
                        break;
                    case CREDIT:
                        credits.add(acc);
                        break;
                    case DEPOSIT:
                        deposits.add(acc);
                        break;
                }
            }
        }
        transactions.sort((Transaction tr1, Transaction tr2) -> tr2.getProcessTime() != null ? tr2.getProcessTime().compareTo(tr1.getProcessTime()) : -1);
        debits.sort((Account acc1, Account acc2)->acc2.getBalance().compareTo(acc1.getBalance()));
        credits.sort((Account acc1, Account acc2)->acc2.getBalance().compareTo(acc1.getBalance()));
        deposits.sort((Account acc1, Account acc2)->acc2.getBalance().compareTo(acc1.getBalance()));
        model.addAttribute("transactions", transactions);
        model.addAttribute("debit", debits);
        model.addAttribute("credit", credits);
        model.addAttribute("deposit", deposits);
        return "user/profile";
    }

    @GetMapping("/user/newaccount")
    public String newAccount(@RequestParam(required = false, name = "card") AccountStatus card, @AuthenticationPrincipal User user, Model model) {
        if (card == null) {
            return "user/newaccount";
        }
       switch(card)
       {
        case DEBIT: 
            accManager.debit(user, "1234");
            return "user/success";
        case DEPOSIT: 
            accManager.deposit(user, "1234");
            return "user/success";
        case CREDIT: 
            accManager.credit(user, "1234");
            return "user/success";
        default: return "user/newaccount";
       }
    }

    @GetMapping("/user/byphone")
    public String byPhone(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("transferForm", new AccToPhoneTransfer());
        model.addAttribute("accounts", accManager.findAccountsByUser(user));
        model.addAttribute("user", user);
        return "user/byphone";
    }

    @PostMapping("/user/byphone")
    public String byPhone(@ModelAttribute AccToPhoneTransfer form, Model model)
    {
        moneySender.sendMoney(form);
        return "redirect:/user/profile";
    }

    @GetMapping("/user/bycard")
    public String byCard(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("transferForm", new AccToCardTransfer());
        model.addAttribute("accounts", accManager.findAccountsByUser(user));
        model.addAttribute("user", user);
        return "user/bycard";
    }

    @PostMapping("/user/bycard")
    public String byCard(@ModelAttribute AccToCardTransfer form, Model model)
    {
        moneySender.sendMoney(form);
        return "redirect:/user/profile";
    }

    @GetMapping("/user/self")
    public String self(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("transferForm", new AccToAccTransfer());
        model.addAttribute("accounts", accManager.findAccountsByUser(user));
        model.addAttribute("user", user);
        return "user/self";
    }

    @PostMapping("user/self")
    public String self(@ModelAttribute AccToAccTransfer form, Model model)
    {
        moneySender.sendMoney(form);
        return "redirect:/user/profile";
    }

}
