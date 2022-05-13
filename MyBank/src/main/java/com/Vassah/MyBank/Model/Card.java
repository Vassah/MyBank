package com.Vassah.MyBank.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.time.OffsetDateTime;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long number;

    private int CVV;

    private String name;

    private OffsetDateTime expirationDateTime;

    private String passwordHash;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    protected Card(){};
    

    /**
     * @return long return the number
     */
    public long getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * @return int return the CVV
     */
    public int getCVV() {
        return CVV;
    }

    /**
     * @param CVV the CVV to set
     */
    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return OffsetDateTime return the expirationDateTime
     */
    public OffsetDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    /**
     * @param expirationDateTime the expirationDateTime to set
     */
    public void setExpirationDateTime(OffsetDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
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
     * @return Account return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

}
