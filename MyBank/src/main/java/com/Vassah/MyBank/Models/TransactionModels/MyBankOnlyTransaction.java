package com.Vassah.MyBank.Models.TransactionModels;

import com.Vassah.MyBank.Models.*;
import com.Vassah.MyBank.Models.AccountModels.*;

public class MyBankOnlyTransaction extends Transaction{
    User Sender;
    Account SenderAccount;
    User Reciever;
    Account RecieverAccount;

    public MyBankOnlyTransaction()
    {

    }
    
    public String ToString()
    {
        return String.format("Номер счёта отправителя: %d \n Номер счёта получателя: $d \n ФИО получателя: %s \n Время отправки: %t", SenderAccount.Number, RecieverAccount.Number, Reciever.shortName(), time);
    }
}