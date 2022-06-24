package com.Vassah.MyBank.Model;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private Currency currency;

    private BigDecimal amount;

    private long senderAccountNumber;
    
    private long recieverAccountNumber;
    
    private OffsetDateTime proccesTime;

    private TransactionType transactionType;

    public Transaction(long _senderAccNum, long _recieverAccNum, BigDecimal _amount, Currency _currency)
    {
        senderAccountNumber = _senderAccNum;
        recieverAccountNumber = _recieverAccNum;
        amount = _amount;
        currency = _currency;
    }
}