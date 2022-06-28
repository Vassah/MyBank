package com.Vassah.MyBank.repositories;

import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.model.Card;
import com.Vassah.MyBank.model.Account;
public interface CardRepository extends CrudRepository<Card, Long>{

    Card findByAccount(Account account);
}
