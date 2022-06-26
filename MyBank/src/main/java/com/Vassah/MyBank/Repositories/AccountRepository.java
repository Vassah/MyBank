package com.Vassah.MyBank.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Model.Card;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long>{
    
    List<Account> findByUser(User user);

    Account findByCard(Card card);

}
