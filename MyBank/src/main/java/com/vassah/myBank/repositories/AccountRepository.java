package com.vassah.myBank.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vassah.myBank.model.Account;
import com.vassah.myBank.model.Card;
import com.vassah.myBank.model.User;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long>{
    
    List<Account> findByUser(User user);

    Account findByCard(Card card);

}
