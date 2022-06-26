package com.Vassah.MyBank.Services;

import com.Vassah.MyBank.Model.AccToAccTransfer;
import com.Vassah.MyBank.Model.AccToCardTransfer;
import com.Vassah.MyBank.Model.AccToPhoneTransfer;
import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.Transaction;
import com.Vassah.MyBank.Model.TransactionType;
import com.Vassah.MyBank.Repositories.AccountRepository;
import com.Vassah.MyBank.Repositories.CardRepository;
import com.Vassah.MyBank.Repositories.TransactionRepository;
import com.Vassah.MyBank.Repositories.UserRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@AllArgsConstructor
public class MoneySender implements Sendable {

    @Autowired
    TransactionRepository transactionRepo;

    @Autowired
    AccountRepository accRepo;

    @Autowired
    UserRepository userRepo;
    
    @Autowired
    CardRepository cardRepo;

    private Transaction CreateTransaction(long senderAccountNumber, long recieverAccountNumber, BigDecimal amount,
            Currency currency) {
        var transaction = new Transaction(senderAccountNumber, recieverAccountNumber, amount, currency);
        transaction.setProccesTime(OffsetDateTime.now());
        return transaction;
    }

    public boolean SendMoney(long senderAccountNumber, long recieverAccountNumber, BigDecimal amount,
            Currency currency) {
        Account sender = accRepo.findById(senderAccountNumber).get();
        if (accRepo.findById(recieverAccountNumber).isPresent()) {
            Account reciever = accRepo.findById(recieverAccountNumber).get();
            if (sender.getCurrency() == reciever.getCurrency()) {
                if (sender.getBalance().add(amount.negate()).compareTo(sender.getBalanceLimit()) == 1) {
                    var transaction = CreateTransaction(senderAccountNumber, recieverAccountNumber, amount, currency);
                    transaction.setTransactionType(TransactionType.MyBankOnly);

                    sender.addTransaction(transaction);
                    accRepo.save(sender);
                    reciever.addTransaction(transaction);
                    accRepo.save(reciever);
                    transactionRepo.save(transaction);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean SendMoney(AccToAccTransfer transfer) {
        Account sender = accRepo.findById(transfer.getSenderAccNumber()).get();
        if (accRepo.findById(transfer.getRecieverAccNumber()).isPresent()) {
            Account reciever = accRepo.findById(transfer.getRecieverAccNumber()).get();
            if (sender.getCurrency() == reciever.getCurrency()) {
                var currency = sender.getCurrency();
                if (sender.getBalance().add(transfer.getAmount().negate()).compareTo(sender.getBalanceLimit()) == 1) {
                    var transaction = CreateTransaction(transfer.getSenderAccNumber(), transfer.getRecieverAccNumber(),
                            transfer.getAmount(), currency);
                    transaction.setTransactionType(TransactionType.MyBankOnly);
                    transaction = transactionRepo.save(transaction);

                    sender.addTransaction(transaction);
                    sender.setBalance(sender.getBalance().subtract(transfer.getAmount()));
                    accRepo.save(sender);

                    reciever.addTransaction(transaction);
                    reciever.setBalance(reciever.getBalance().add(transfer.getAmount()));
                    accRepo.save(reciever);

                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean SendMoney(AccToPhoneTransfer transfer) {
        System.out.println("тест епта");
        Account sender = accRepo.findById(transfer.getSenderAccNumber()).get();
        System.out.println("успех епта");
        var receiverUser = userRepo.findByPhoneNumber(transfer.getRecieverPhoneNumber());
        if (receiverUser != null) 
        {
            var possibleAccs = accRepo.findByUser(receiverUser);
            var currency = sender.getCurrency();
            if (!possibleAccs.isEmpty()) 
            {
                possibleAccs.sort((Account a1, Account a2) -> a1.getBalance().compareTo(a2.getBalance()));
                Account recieverAcc = null;
                for (Account acc : possibleAccs)
                {
                    if (acc.getCurrency() == currency)
                    {
                        recieverAcc = acc;
                        break;
                    }
                }
                if (recieverAcc != null) 
                {
                    if (sender.getBalance().add(transfer.getAmount().negate())
                            .compareTo(sender.getBalanceLimit()) == 1) {
                        var transaction = CreateTransaction(transfer.getSenderAccNumber(),
                                recieverAcc.getNumber(), transfer.getAmount(), currency);
                        transaction.setTransactionType(TransactionType.MyBankOnly);
                        transaction = transactionRepo.save(transaction);

                        sender.addTransaction(transaction);
                        sender.setBalance(sender.getBalance().subtract(transfer.getAmount()));
                        accRepo.save(sender);

                        recieverAcc.addTransaction(transaction);
                        recieverAcc.setBalance(recieverAcc.getBalance().add(transfer.getAmount()));
                        accRepo.save(recieverAcc);

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean SendMoney(AccToCardTransfer transfer) {
        Account sender = accRepo.findById(transfer.getSenderAccNumber()).get();
        Long cardNumber = Long.parseLong(transfer.getRecieverCardNumber().substring(8));
        var reciever = accRepo.findByCard(cardRepo.findById(cardNumber).get());
        if (reciever != null) 
        {
                if(sender.getCurrency() == reciever.getCurrency())
                {
                    var currency = sender.getCurrency();
                    if (sender.getBalance().add(transfer.getAmount().negate())
                            .compareTo(sender.getBalanceLimit()) == 1) {
                        var transaction = CreateTransaction(transfer.getSenderAccNumber(),
                                reciever.getNumber(), transfer.getAmount(), currency);
                        transaction.setTransactionType(TransactionType.MyBankOnly);
                        transaction = transactionRepo.save(transaction);

                        sender.addTransaction(transaction);
                        sender.setBalance(sender.getBalance().subtract(transfer.getAmount()));
                        accRepo.save(sender);

                        reciever.addTransaction(transaction);
                        reciever.setBalance(reciever.getBalance().add(transfer.getAmount()));
                        accRepo.save(reciever);

                        return true;
                    }
                }
        }
        return false;
    }
}
