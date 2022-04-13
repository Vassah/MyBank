package com.Vassah.MyBank.Controllers;

import com.Vassah.MyBank.Entity.UserEntity;
import com.Vassah.MyBank.Repositories.UserRepository;

import org.h2.engine.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration_page")
public class RegistrationController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping
    public ResponseEntity registrate(@RequestBody UserEntity user){
        try {
            UserBuilder.registration(user);
            return ResponseEntity.ok("Пользователь сохранён");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }

    }

    @PostMapping
    public ResponseEntity getOneUser(@RequestParam Long id){
        try{
            return ResponseEntity.ok(UserBuilder.getOne(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try{
            return ResponseEntity.ok(UserBuilder.delete(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

}

