package com.Vassah.MyBank.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.Model.Card;
import com.Vassah.MyBank.Model.Account;
public interface CardRepository extends CrudRepository<Card, Long>{

    Card findByAccount(Account account);
}
