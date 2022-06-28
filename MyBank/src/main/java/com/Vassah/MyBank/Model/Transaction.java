package com.Vassah.MyBank.model;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
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

    @Column(name="sender_number")
    private long senderAccountNumber;
    
    @Column(name="reciever_number")
    private long recieverAccountNumber;
    
    private OffsetDateTime processTime;

    private TransactionType transactionType;

    public Transaction(long _senderAccNum, long _recieverAccNum, BigDecimal _amount, Currency _currency)
    {
        senderAccountNumber = _senderAccNum;
        recieverAccountNumber = _recieverAccNum;
        amount = _amount;
        currency = _currency;
    }

    public String getTime()
    {
        return processTime.format(DateTimeFormatter.ofPattern("hh:mm"));
    }

    public String getDate()
    {
        return processTime.format(DateTimeFormatter.ofPattern("d MMM uuuu"));
    }

    public String getDateTime()
    {
        return processTime.format(DateTimeFormatter.ofPattern("hh:mm, d MMM uuuu"));
    }
}