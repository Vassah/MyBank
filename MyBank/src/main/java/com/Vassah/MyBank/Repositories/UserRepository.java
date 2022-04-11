package com.Vassah.MyBank.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.Model.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
}
