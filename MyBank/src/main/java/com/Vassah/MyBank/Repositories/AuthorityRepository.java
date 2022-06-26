package com.Vassah.MyBank.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Vassah.MyBank.Model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long>{
    public Optional<Authority> findByName(String name);
    
}
