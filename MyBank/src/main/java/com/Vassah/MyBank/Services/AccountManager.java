package com.Vassah.MyBank.Services;

import com.Vassah.MyBank.Exceptions.UserAlreadyExistException;
import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class AccountManager {
    @Autowired
    private UserRepository userRepo;


    public void createUser(User user) throws UserAlreadyExistException
    {   User userFromDB = userRepo.findByFirstName(user.getFirstName());

        if(userFromDB!=null){
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        else{userRepo.save(user);}


    };

    public void deleteUser(User user){
        userRepo.delete(user);
    }


}
