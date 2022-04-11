package com.Vassah.MyBank.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    private Account[] accounts;

    private String firstName;

    private String lastName;

    private String middleName;

    private String passwordHash;

    private BankStatus status;
    
    protected User()
    {

    }

    public String shortName() { return lastName + String.valueOf(firstName.toCharArray()[0]) + ".";}

    public String fullName() { return firstName + middleName + lastName; }

}
