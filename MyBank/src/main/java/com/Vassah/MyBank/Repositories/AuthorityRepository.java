package com.Vassah.MyBank.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Vassah.MyBank.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long>{
    public Optional<Authority> findByName(String name);
    
}
