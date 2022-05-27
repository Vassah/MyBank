package com.Vassah.MyBank.Services;

import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.Transaction;
import com.Vassah.MyBank.Model.TransactionType;
import com.Vassah.MyBank.Repositories.AccountRepository;
import com.Vassah.MyBank.Repositories.TransactionRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class MoneySender implements Sendable{

    @Autowired
    TransactionRepository transactionRepo;

    @Autowired
    AccountRepository accountRepository;

    private static MoneySender instance;

    public static MoneySender GetInstance()
    {
        if (instance == null)
        {
            return new MoneySender();
        }
        return MoneySender.instance;
    
    }

    private Transaction CreateTransaction(long senderAccountNumber, long recieverAccountNumber, BigDecimal amount, Currency currency)
    {
        var transaction = new Transaction(senderAccountNumber, recieverAccountNumber, amount, currency);
        transaction.setProccesTime(OffsetDateTime.now());
        return transaction;
    }

    public Transaction SendMoney(long senderAccountNumber, long recieverAccountNumber, BigDecimal amount, Currency currency) 
    {
        Account sender = accountRepository.findById(senderAccountNumber).get();
        if (accountRepository.findById(recieverAccountNumber).isPresent())
        {
            Account reciever = accountRepository.findById(recieverAccountNumber).get();
            if (sender.getCurrency() == reciever.getCurrency())
            {
                if (sender.getBalance().add( amount.negate()).compareTo(sender.getBalanceLimit()) == 1)
                { 
                    var transaction = CreateTransaction(senderAccountNumber, recieverAccountNumber, amount, currency);
                    transaction.setTransactionType(TransactionType.MyBankOnly);

                    sender.addTransaction(transaction);
                    accountRepository.save(sender);
                    reciever.addTransaction(transaction);
                    accountRepository.save(reciever);
                    transactionRepo.save(transaction);
                    return transaction;
                }
            }
            return null;
        }
        return null;
    }

    
}
