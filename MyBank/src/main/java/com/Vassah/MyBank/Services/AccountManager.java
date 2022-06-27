package com.Vassah.MyBank.Services;

import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.AccountStatus;
import com.Vassah.MyBank.Model.Card;
import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Repositories.AccountRepository;
import com.Vassah.MyBank.Repositories.CardRepository;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Currency;
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

    public void debit(User user, String password)
    {
        var debit = new Account();
        debit.setCurrency(Currency.getInstance("RUB"));
        debit.setStatus(AccountStatus.Debit);
        debit.setBalance(BigDecimal.valueOf(0));
        debit.setBalanceLimit(BigDecimal.valueOf(0));
        debit.setUser(user);
        debit.setMultiplicator(100);
        var card = new Card();
        card.setCVV(123);
        card.setExpiration((String.join("/", OffsetDateTime.now().getMonth().toString(), String.valueOf(OffsetDateTime.now().plus(5l, ChronoUnit.YEARS).getYear()))));
        card.setName(user.fullName());
        card.setPasswordHash(passwordEncoder.encode(password));
        card.setAccount(debit);
        card = cardRepo.save(card);
        debit = card.getAccount();
        debit.setCard(card);
        debit = accountRepo.save(debit);
    }

    public void credit(User user, String password)
    {
        var credit = new Account();
        credit.setCurrency(Currency.getInstance("RUB"));
        credit.setStatus(AccountStatus.Credit);
        credit.setBalance(BigDecimal.valueOf(0));
        credit.setBalanceLimit(BigDecimal.valueOf(-300000));
        credit.setUser(user);
        credit.setMultiplicator(95);
        var card = new Card();
        card.setCVV(123);
        card.setExpiration((String.join("/", OffsetDateTime.now().getMonth().toString(), String.valueOf(OffsetDateTime.now().plus(5l, ChronoUnit.YEARS).getYear()))));
        card.setName(user.fullName());
        card.setPasswordHash(passwordEncoder.encode(password));
        card.setAccount(credit);
        card = cardRepo.save(card);
        credit = card.getAccount();
        credit.setCard(card);
        credit = accountRepo.save(credit);
    }

    public void deposit(User user, String password)
    {
        var deposit = new Account();
        deposit.setCurrency(Currency.getInstance("USD"));
        deposit.setStatus(AccountStatus.Deposit);
        deposit.setBalance(BigDecimal.valueOf(0));
        deposit.setBalanceLimit(BigDecimal.valueOf(0));
        deposit.setUser(user);
        deposit.setMultiplicator(110);
        var card = new Card();
        card.setCVV(123);
        card.setExpiration((String.join("/", OffsetDateTime.now().getMonth().toString(), String.valueOf(OffsetDateTime.now().plus(5l, ChronoUnit.YEARS).getYear()))));
        card.setName(user.fullName());
        card.setPasswordHash(passwordEncoder.encode(password));
        card.setAccount(deposit);
        card = cardRepo.save(card);
        deposit = card.getAccount();
        deposit.setCard(card);
        deposit = accountRepo.save(deposit);
    }

    public List<Account> findAccountsByUser(User user)
    {
        return accountRepo.findByUser(user);
    }

}
