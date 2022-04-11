package com.Vassah.MyBank.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long Number;

    public User User;
    
    public long Balance;
    
    public Transaction[] Transactions;

    public abstract void withdrawal();
    public abstract void replenishment();

}
