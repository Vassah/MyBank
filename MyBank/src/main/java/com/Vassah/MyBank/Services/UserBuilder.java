package com.Vassah.MyBank.Services;

import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Model.UserStatus;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;

public class UserBuilder {
    private String firstName;

    private String lastName;

    private String middleName;

    private String passwordHash;

    private String phoneNumber;

    private UserStatus status;


    public UserBuilder(){
    }

    public UserBuilder(String _fName, String _lName)
    {
        firstName = _fName;
        lastName = _lName;
    }

    public User toUser()
    {
        return new User(firstName, lastName, middleName, passwordHash, phoneNumber, status);
    }
}
