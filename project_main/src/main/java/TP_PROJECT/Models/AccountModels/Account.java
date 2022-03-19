package TP_PROJECT.Models.AccountModels;

public abstract class Account {
    long Number;
    User User;
    long Balance;
    Transaction[] Transactions;

    public abstract void withdrawal();
    public abstract void replenishment();

}
