 package com.vassah.myBank.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vassah.myBank.model.AccToAccTransfer;
import com.vassah.myBank.model.AccToCardTransfer;
import com.vassah.myBank.model.AccToPhoneTransfer;
import com.vassah.myBank.model.Account;
import com.vassah.myBank.model.Transaction;
import com.vassah.myBank.model.TransactionType;
import com.vassah.myBank.repositories.AccountRepository;
import com.vassah.myBank.repositories.CardRepository;
import com.vassah.myBank.repositories.TransactionRepository;
import com.vassah.myBank.repositories.UserRepository;

@Service
@Component
@AllArgsConstructor
public class MoneySender {

    @Autowired
    TransactionRepository transactionRepo;

    @Autowired
    AccountRepository accRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    CardRepository cardRepo;

    private Transaction createTransaction(long senderAccountNumber, long recieverAccountNumber, BigDecimal amount, Currency currency) {
        var transaction = new Transaction(senderAccountNumber, recieverAccountNumber, amount, currency);
        transaction.setProcessTime(OffsetDateTime.now());
        return transaction;
    }

    public boolean sendMoney(AccToAccTransfer transfer) {
        Account sender = accRepo.findById(transfer.getSenderAccNumber()).get();
        if (accRepo.findById(transfer.getRecieverAccNumber()).isPresent()) {
            Account reciever = accRepo.findById(transfer.getRecieverAccNumber()).get();
            if (sender.getCurrency() == reciever.getCurrency()) {
                var currency = sender.getCurrency();
                if (sender.getBalance().add(transfer.getAmount().negate()).compareTo(sender.getBalanceLimit()) == 1) {
                    var transaction = createTransaction(transfer.getSenderAccNumber(), transfer.getRecieverAccNumber(),
                            transfer.getAmount(), currency);
                    transaction.setTransactionType(TransactionType.myBank_ONLY);
                    transaction = transactionRepo.save(transaction);

                    sender.addSendTransaction(transaction);
                    sender.setBalance(sender.getBalance().subtract(transfer.getAmount()));
                    accRepo.save(sender);

                    reciever.addRecieveTransaction(transaction);
                    reciever.setBalance(reciever.getBalance().add(transfer.getAmount()));
                    accRepo.save(reciever);

                    return true;
                }
            }
        }
        return false;
    }

    public boolean sendMoney(AccToPhoneTransfer transfer) {
        Account sender = accRepo.findById(transfer.getSenderAccNumber()).get();
        var receiverUser = userRepo.findByPhoneNumber(transfer.getRecieverPhoneNumber());
        if (receiverUser != null) {
            var possibleAccs = accRepo.findByUser(receiverUser);
            var currency = sender.getCurrency();
            if (!possibleAccs.isEmpty()) {
                possibleAccs.sort((Account a1, Account a2) -> a1.getBalance().compareTo(a2.getBalance()));
                Account recieverAcc = null;
                for (Account acc : possibleAccs) {
                    if (acc.getCurrency() == currency) {
                        recieverAcc = acc;
                        break;
                    }
                }
                if (recieverAcc != null) {
                    if (sender.getBalance().add(transfer.getAmount().negate())
                            .compareTo(sender.getBalanceLimit()) == 1) {
                        var transaction = createTransaction(transfer.getSenderAccNumber(),
                                recieverAcc.getNumber(), transfer.getAmount(), currency);
                        transaction.setTransactionType(TransactionType.myBank_ONLY);
                        transaction = transactionRepo.save(transaction);

                        sender.addSendTransaction(transaction);
                        sender.setBalance(sender.getBalance().subtract(transfer.getAmount()));
                        sender = accRepo.save(sender);

                        recieverAcc.addRecieveTransaction(transaction);
                        recieverAcc.setBalance(recieverAcc.getBalance().add(transfer.getAmount()));
                        accRepo.save(recieverAcc);

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean sendMoney(AccToCardTransfer transfer) {
        Account sender = accRepo.findById(transfer.getSenderAccNumber()).get();
        Long cardNumber = Long.parseLong(transfer.getRecieverCardNumber().replace(" ", "").substring(8));
        var reciever = accRepo.findByCard(cardRepo.findById(cardNumber).get());
        if (reciever != null) {
            if (sender.getCurrency() == reciever.getCurrency()) {
                var currency = sender.getCurrency();
                if (sender.getBalance().add(transfer.getAmount().negate())
                        .compareTo(sender.getBalanceLimit()) == 1) {
                    var transaction = createTransaction(transfer.getSenderAccNumber(),
                            reciever.getNumber(), transfer.getAmount(), currency);
                    transaction.setTransactionType(TransactionType.myBank_ONLY);
                    transaction = transactionRepo.save(transaction);

                    sender.addSendTransaction(transaction);
                    sender.setBalance(sender.getBalance().subtract(transfer.getAmount()));
                    accRepo.save(sender);

                    reciever.addRecieveTransaction(transaction);
                    reciever.setBalance(reciever.getBalance().add(transfer.getAmount()));
                    accRepo.save(reciever);

                    return true;
                }
            }
        }
        return false;
    }
}
