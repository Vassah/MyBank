package com.Vassah.MyBank.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Currency;

@Entity
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long number;

    private User user;
    
    private Currency balance;
    
    private Transaction[] transactions;

    private Card card;

    private AccountStatus status;

    protected Account(){};

}
