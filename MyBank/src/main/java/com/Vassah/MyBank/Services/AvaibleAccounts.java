package com.Vassah.MyBank.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Currency;

import com.Vassah.MyBank.model.Account;
import com.Vassah.MyBank.model.AccountStatus;
import com.Vassah.MyBank.model.Card;

public class AvaibleAccounts {

    public static Account getDebit() {
        var debit = new Account();
        debit.setCurrency(Currency.getInstance("RUB"));
        debit.setStatus(AccountStatus.DEBIT);
        debit.setBalance(BigDecimal.valueOf(0));
        debit.setBalanceLimit(BigDecimal.valueOf(0));
        debit.setMultiplicator(100);
        return debit;
    }

    public static Account getCredit() {
        var credit = new Account();
        credit.setCurrency(Currency.getInstance("RUB"));
        credit.setStatus(AccountStatus.CREDIT);
        credit.setBalance(BigDecimal.valueOf(0));
        credit.setBalanceLimit(BigDecimal.valueOf(-300000));
        credit.setMultiplicator(95);
        return credit;
    }

    public static Account getDeposit() {
        var deposit = new Account();
        deposit.setCurrency(Currency.getInstance("USD"));
        deposit.setStatus(AccountStatus.DEPOSIT);
        deposit.setBalance(BigDecimal.valueOf(0));
        deposit.setBalanceLimit(BigDecimal.valueOf(0));
        deposit.setMultiplicator(110);
        return deposit;
    }

    public static Card getCard() {
        var card = new Card();
        card.setCVV(123);
        card.setExpiration((String.join("/", OffsetDateTime.now().getMonth().toString(),
                String.valueOf(OffsetDateTime.now().plus(5l, ChronoUnit.YEARS).getYear()))));
        return card;
    }

}
