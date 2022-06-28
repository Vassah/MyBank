package com.Vassah.MyBank.repositories;

import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.model.Account;
import com.Vassah.MyBank.model.User;
import com.Vassah.MyBank.model.Card;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long>{
    
    List<Account> findByUser(User user);

    Account findByCard(Card card);

}
