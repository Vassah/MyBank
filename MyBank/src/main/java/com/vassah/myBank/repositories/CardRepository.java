package com.vassah.myBank.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vassah.myBank.model.Account;
import com.vassah.myBank.model.Card;
public interface CardRepository extends CrudRepository<Card, Long>{

    Card findByAccount(Account account);
}
