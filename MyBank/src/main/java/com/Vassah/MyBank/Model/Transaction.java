package com.Vassah.MyBank.Model;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Currency;

@Entity
public class Transaction{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private Currency amount;

    private long senderAccountNumber;
    
    private long recieverAccountNumber;
    
    private OffsetDateTime proccesTime;

    private TransactionType type;

    protected Transaction() {}


    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return Currency return the amount
     */
    public Currency getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Currency amount) {
        this.amount = amount;
    }

    /**
     * @return long return the senderAccountNumber
     */
    public long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    /**
     * @param senderAccountNumber the senderAccountNumber to set
     */
    public void setSenderAccountNumber(long senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    /**
     * @return long return the recieverAccountNumber
     */
    public long getRecieverAccountNumber() {
        return recieverAccountNumber;
    }

    /**
     * @param recieverAccountNumber the recieverAccountNumber to set
     */
    public void setRecieverAccountNumber(long recieverAccountNumber) {
        this.recieverAccountNumber = recieverAccountNumber;
    }

    /**
     * @return OffsetDateTime return the proccesTime
     */
    public OffsetDateTime getProccesTime() {
        return proccesTime;
    }

    /**
     * @param proccesTime the proccesTime to set
     */
    public void setProccesTime(OffsetDateTime proccesTime) {
        this.proccesTime = proccesTime;
    }

    /**
     * @return TransactionType return the type
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TransactionType type) {
        this.type = type;
    }

}