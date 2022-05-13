package com.Vassah.MyBank.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String passwordHash;

    private String phoneNumber;

    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts = new HashSet<Account>();
    
    protected User(){}

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getName(){return firstName;}
    public void setName(String firstName){this.firstName=firstName;}



    public String shortName() { return lastName + String.valueOf(firstName.toCharArray()[0]) + ".";}
    public String fullName() { return firstName + middleName + lastName; }

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return String return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return String return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash the passwordHash to set
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * @return String return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
<<<<<<< HEAD
     * @return UserStatus return the status
=======
     * @return UserStatus return the status
>>>>>>> server_settings
     */
    public UserStatus getStatus() {
        UpdateUserStatus();
        return status;
    }

    private void UpdateUserStatus()
    {

    }

    /**
     * @param status the status to set
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * @return Set<Account> return the accounts
     */
    public Set<Account> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

}
