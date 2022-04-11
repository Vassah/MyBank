package com.Vassah.MyBank.Model;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long Id;

    float Amount;
    long SenderAccountNumber;
    long RecieverAccountNumber;
    OffsetDateTime time;

    protected Transaction() {}

}