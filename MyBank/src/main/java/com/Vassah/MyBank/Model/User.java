package com.Vassah.MyBank.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String passwordHash;

    private String phoneNumber;

    private UserStatus status;

    @OneToMany(mappedBy = "user")
    @Setter(AccessLevel.NONE)
    private Set<Account> accounts = new HashSet<Account>();
    
    protected User(){}

    public User(String _fName, String _lName, String _mName, String _pHash, String _pNumber, UserStatus _uStatus){
        firstName = _fName;
        lastName = _lName;
        middleName = _mName;
        passwordHash = _pHash;
        phoneNumber = _pNumber;
        status = _uStatus;
    }

    public Long getId(){return id;}

    public String shortName() { return lastName + String.valueOf(firstName.toCharArray()[0]) + ".";}
    
    public String fullName() { return firstName + middleName + lastName; }

    public void AddAccount (Account account)
    {
        accounts.add(account);
    }

}
