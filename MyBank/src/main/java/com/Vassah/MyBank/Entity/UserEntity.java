package com.Vassah.MyBank.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String passwordHash;

    public UserEntity(){}

    public void setId(Long id){this.id = id;}
    public Long getId(){return id;}
    public void setName(String firstName){this.firstName = firstName;}
    public String getName(){return firstName;}



}
