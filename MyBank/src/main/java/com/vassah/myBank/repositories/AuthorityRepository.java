package com.vassah.myBank.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vassah.myBank.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long>{
    public Optional<Authority> findByName(String name);
    
}
