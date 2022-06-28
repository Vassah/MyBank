package com.Vassah.MyBank.repositories;

import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);

    User findByVerificationCode(String code);
}
