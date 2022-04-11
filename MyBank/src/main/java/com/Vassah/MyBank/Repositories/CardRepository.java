package com.Vassah.MyBank.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.Vassah.MyBank.Model.Card;

public interface CardRepository extends CrudRepository<Card, Long>{
    
}
