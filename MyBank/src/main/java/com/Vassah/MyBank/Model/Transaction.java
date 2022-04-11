package com.Vassah.MyBank.Model;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Currency;

@Entity
public class Transaction{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private Currency amount;

    private long senderAccountNumber;
    
    private long recieverAccountNumber;
    
    private OffsetDateTime proccesTime;

    private TransactionType type;

    protected Transaction() {}

}