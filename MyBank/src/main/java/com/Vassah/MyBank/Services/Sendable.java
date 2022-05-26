package com.Vassah.MyBank.Services;

public interface Sendable {

    public com.Vassah.MyBank.Model.Transaction SendMoney(long senderAccountNumber, long recieverAccountNumber, java.math.BigDecimal amount, java.util.Currency currency);

    
}
