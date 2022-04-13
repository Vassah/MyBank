package com.Vassah.MyBank.Model;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/

import com.Vassah.MyBank.Entity.UserEntity;

public class User {
    public long id;

    private Account[] accounts;

    private String firstName;

    private String lastName;

    private String middleName;

    private String passwordHash;

    private String phoneNumber;

    private TransactionType status;
    
    protected User()
    {

    }

    public static User toModel(UserEntity entity){
        User model = new User();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getName(){return firstName;}
    public void setName(String firstName){this.firstName=firstName;}

    public String shortName() { return lastName + String.valueOf(firstName.toCharArray()[0]) + ".";}

    public String fullName() { return firstName + middleName + lastName; }

}
