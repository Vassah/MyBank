 package com.Vassah.MyBank.Services;

import com.Vassah.MyBank.Model.AccToAccTransfer;
import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.Transaction;
import com.Vassah.MyBank.Model.TransactionType;
import com.Vassah.MyBank.Repositories.AccountRepository;
import com.Vassah.MyBank.Repositories.TransactionRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@AllArgsConstructor
public class MoneySender implements Sendable{

    @Autowired
    TransactionRepository transactionRepo;

    @Autowired
    AccountRepository accountRepository;

    private Transaction CreateTransaction(long senderAccountNumber, long recieverAccountNumber, BigDecimal amount, Currency currency)
    {
        var transaction = new Transaction(senderAccountNumber, recieverAccountNumber, amount, currency);
        transaction.setProccesTime(OffsetDateTime.now());
        return transaction;
    }

    public boolean SendMoney(long senderAccountNumber, long recieverAccountNumber, BigDecimal amount, Currency currency) 
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
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean SendMoney(AccToAccTransfer transfer) 
    {
        Account sender = accountRepository.findById(transfer.getSenderNumber()).get();
        if (accountRepository.findById(transfer.getRecieverNumber()).isPresent())
        {
            Account reciever = accountRepository.findById(transfer.getRecieverNumber()).get();
            if (sender.getCurrency() == reciever.getCurrency())
            {
                var currency = sender.getCurrency();
                if (sender.getBalance().add( transfer.getAmount().negate()).compareTo(sender.getBalanceLimit()) == 1)
                { 
                    var transaction = CreateTransaction(transfer.getSenderNumber(), transfer.getRecieverNumber(), transfer.getAmount(), currency);
                    transaction.setTransactionType(TransactionType.MyBankOnly);
                    transaction = transactionRepo.save(transaction);

                    sender.addTransaction(transaction);
                    sender.setBalance(sender.getBalance().subtract(transfer.getAmount()));
                    accountRepository.save(sender);
                    
                    reciever.addTransaction(transaction);
                    reciever.setBalance(reciever.getBalance().add(transfer.getAmount()));
                    accountRepository.save(reciever);
                    
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    
}
