package TP_PROJECT.Model;

public abstract class Account {
    int number;
    User user;
    //Transaction
    int balance;

    public abstract void withdrawal();
    public abstract void replenishment();

}
