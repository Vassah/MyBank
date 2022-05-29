package com.Vassah.MyBank.Services;

import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Repositories.AccountRepository;
import com.Vassah.MyBank.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@Component
@AllArgsConstructor
public class AccountManager {
    @Autowired
    private final UserRepository userRepo;
    @Autowired
    private final AccountRepository accountRepo;

    public void AddAccountToUser(User user, Account acc)
    {
        user.AddAccount(acc);
        userRepo.save(user);
        acc.setUser(user);
        accountRepo.save(acc);
    }

}
