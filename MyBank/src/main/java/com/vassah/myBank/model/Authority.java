package com.vassah.myBank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="authorities")
@NoArgsConstructor
public class Authority implements GrantedAuthority{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    
    @Override
    public String getAuthority()
    {
        return name;
    }
    public Authority(long l, String string) {
        id = l; name = string;
    }
}
