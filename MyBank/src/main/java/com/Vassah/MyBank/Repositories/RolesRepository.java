package com.Vassah.MyBank.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Vassah.MyBank.Model.Role;

public interface RolesRepository extends CrudRepository<Role, Long>{
    public Optional<Role> findByName(String name);
    
}
