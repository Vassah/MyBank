package com.Vassah.MyBank.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import com.Vassah.MyBank.Model.Account;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long number;

    private int CVV;

    private String name;

    private OffsetDateTime expirationDateTime;

    private String passwordHash;

    private Account account;

    protected Card(){};
    
}
