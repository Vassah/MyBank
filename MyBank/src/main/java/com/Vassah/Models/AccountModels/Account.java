package com.Vassah.MyBank.Models.AccountModels;

import com.Vassah.MyBank.Models.*;
import com.Vassah.MyBank.Models.TransactionModels.*;

public abstract class Account {
    public long Number;
    public User User;
    public long Balance;
    public Transaction[] Transactions;

    public abstract void withdrawal();
    public abstract void replenishment();

}
