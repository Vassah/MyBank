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

    public Account[] accounts;

    public String firstName;

    public String lastName;

    public String middleName;

    public String passwordHash;
    
    public User()
    {

    }

    public String shortName() { return lastName + String.valueOf(firstName.toCharArray()[0]) + ".";}
    public String fullName() { return firstName + middleName + lastName; }

}
