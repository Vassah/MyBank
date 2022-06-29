package com.vassah.my_bank.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vassah.my_bank.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long>{
    public Optional<Authority> findByName(String name);
    
}
