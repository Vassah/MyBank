package com.vassah.my_bank.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
    @UniqueConstraint(name="phoneNumber", columnNames = "phoneNumber"),
    @UniqueConstraint(name="email", columnNames = "email")
}, name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    private String phoneNumber;

    private String email;

    private String firstName;

    private String lastName;

    private String middleName;

    private String passwordHash;

    @Transient
    private String passwordConfirmHash;

    private String verificationCode;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    public String shortName() { return lastName + " " + firstName.charAt(0) + ".";}
    
    public String fullName() { return String.join(" ", firstName, lastName); }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}