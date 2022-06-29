package com.vassah.my_bank.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vassah.my_bank.model.Account;
import com.vassah.my_bank.model.Card;
public interface CardRepository extends CrudRepository<Card, Long>{

    Card findByAccount(Account account);
}
