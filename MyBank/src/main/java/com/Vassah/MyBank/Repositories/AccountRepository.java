package com.Vassah.MyBank.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Model.Card;
import java.util.Collection;

public interface AccountRepository extends CrudRepository<Account, Long>{
    
    Collection<Account> findByUser(User user);

    Account findByCard(Card card);

}
