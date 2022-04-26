package com.Vassah.MyBank.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.Model.Account;
import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Model.Card;
import java.util.Collection;

public interface AccountRepository extends CrudRepository<Account, Long>{
    
    Collection<Account> findByUser(User user);

    //@Query("select acc from AccountRepository where acc.user.id = userId")
    //Collection<Account> findByUserId(long userId);

    Account findByCard(Card card);

    //@Query("select acc from AccountRepository where acc.card.number = cardNumber")
    //Account findByCardNumber(long number);

}
