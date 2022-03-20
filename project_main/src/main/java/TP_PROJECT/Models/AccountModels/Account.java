package TP_PROJECT.Models.AccountModels;

import TP_PROJECT.Models.*;
import TP_PROJECT.Models.TransactionModels.*;

public abstract class Account {
    public long Number;
    public User User;
    public long Balance;
    public Transaction[] Transactions;

    public abstract void withdrawal();
    public abstract void replenishment();

}
