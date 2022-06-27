package com.Vassah.MyBank.Services;

public interface Sendable {

    public boolean SendMoney(long senderAccountNumber, long recieverAccountNumber, java.math.BigDecimal amount, java.util.Currency currency);

    
}
