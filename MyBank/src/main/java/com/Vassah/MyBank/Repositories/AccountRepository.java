package com.Vassah.MyBank.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.Model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
    
}
