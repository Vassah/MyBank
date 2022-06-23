package com.Vassah.MyBank.Model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="user_roles")
@NoArgsConstructor
public class Role implements GrantedAuthority{
    
    @GeneratedValue
    @Id
    private Long id;

    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    @Override
    public String getAuthority()
    {
        return name;
    }
    public Role(long l, String string) {
        id = l; name = string;
    }
}
