package com.vassah.myBank.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vassah.myBank.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);

    User findByVerificationCode(String code);
}
