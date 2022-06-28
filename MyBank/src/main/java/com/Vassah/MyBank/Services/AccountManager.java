package com.Vassah.MyBank.services;

import com.Vassah.MyBank.model.Account;
import com.Vassah.MyBank.model.User;
import com.Vassah.MyBank.repositories.AccountRepository;
import com.Vassah.MyBank.repositories.CardRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@Component
@AllArgsConstructor
public class AccountManager {

    @Autowired
    private final AccountRepository accountRepo;

    @Autowired
    private final CardRepository cardRepo;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public void debit(User user, String password) {
        var debit = AvaibleAccounts.getDebit();
        debit.setUser(user);

        var card = AvaibleAccounts.getCard();
        card.setName(user.fullName());
        card.setPasswordHash(passwordEncoder.encode(password));
        card.setAccount(debit);
        card = cardRepo.save(card);

        debit = card.getAccount();
        debit.setCard(card);
        debit = accountRepo.save(debit);
    }

    public void credit(User user, String password) {
        var credit = AvaibleAccounts.getCredit();
        credit.setUser(user);

        var card = AvaibleAccounts.getCard();
        card.setName(user.fullName());
        card.setPasswordHash(passwordEncoder.encode(password));
        card.setAccount(credit);
        card = cardRepo.save(card);

        credit = card.getAccount();
        credit.setCard(card);
        credit = accountRepo.save(credit);
    }

    public void deposit(User user, String password) {
        var deposit = AvaibleAccounts.getDeposit();
        deposit.setUser(user);

        var card = AvaibleAccounts.getCard();
        card.setName(user.fullName());
        card.setPasswordHash(passwordEncoder.encode(password));
        card.setAccount(deposit);
        card = cardRepo.save(card);

        deposit = card.getAccount();
        deposit.setCard(card);
        deposit = accountRepo.save(deposit);
    }

    public List<Account> findAccountsByUser(User user) {
        return accountRepo.findByUser(user);
    }

}
