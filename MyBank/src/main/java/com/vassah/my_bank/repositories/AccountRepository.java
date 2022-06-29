package com.vassah.my_bank.repositories;

import org.springframework.data.repository.CrudRepository;

import com.vassah.my_bank.model.Account;
import com.vassah.my_bank.model.Card;
import com.vassah.my_bank.model.User;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long>{
    
    List<Account> findByUser(User user);

    Account findByCard(Card card);

}
