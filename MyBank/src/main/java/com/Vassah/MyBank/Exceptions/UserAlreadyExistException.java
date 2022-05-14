package com.Vassah.MyBank.Exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message){
        super(message);
    }
}
