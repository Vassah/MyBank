package com.Vassah.MyBank.Models.TransactionModels;
import java.time.OffsetDateTime;

public abstract class Transaction{
    long id;
    float amount;
    long SenderAccountNumber;
    long RecieverAccountNumber;
    OffsetDateTime time;
}