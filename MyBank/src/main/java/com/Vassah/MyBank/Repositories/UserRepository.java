package com.Vassah.MyBank.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
    User findByAccount(Account account);

    @Query("Select u from UserRepository where u.a")
    User findByAccountNumber(long accountNumber);

    User findByPhoneNumber(String phoneNumber);

    
}
