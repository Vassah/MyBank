package com.vassah.my_bank.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vassah.my_bank.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);

    User findByVerificationCode(String code);
}
