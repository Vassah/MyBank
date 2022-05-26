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

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getName(){return firstName;}
    public void setName(String firstName){this.firstName=firstName;}



    public String shortName() { return lastName + String.valueOf(firstName.toCharArray()[0]) + ".";}
    public String fullName() { return firstName + middleName + lastName; }

    public void AddAccount (Account account)
    {
        accounts.add(account);
    }

}
