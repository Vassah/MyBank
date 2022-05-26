package com.Vassah.MyBank.Model;
import java.math.BigDecimal;
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

    private Currency currency;

    private BigDecimal amount;

    private long senderAccountNumber;
    
    private long recieverAccountNumber;
    
    private OffsetDateTime proccesTime;

    private TransactionType type;

    protected Transaction() {}

    public Transaction(long _senderAccNum, long _recieverAccNum, BigDecimal _amount, Currency _curr)
    {
        senderAccountNumber = _senderAccNum;
        recieverAccountNumber = _recieverAccNum;
        amount = _amount; 
        currency = _curr;

    }

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
     * @return BigDecimal return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }


    /**
     * @return long return the senderAccountNumber
     */
    public long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    /**
     * @return long return the recieverAccountNumber
     */
    public long getRecieverAccountNumber() {
        return recieverAccountNumber;
    }

    /**
     * @return OffsetDateTime return the proccesTime
     */
    public OffsetDateTime getProccesTime() {
        return proccesTime;
    }

    /**
     * @param OffsetDateTime the process to set
     */

     public void setProccesTime(OffsetDateTime time)
     {
         proccesTime = time;
     }

    /**
     * @return TransactionType return the type
     */
    public TransactionType getType() {
        return type;
    }

    public void setTransactionType(TransactionType _type)
    {
        type = _type;
    }

    public Currency getCurrency()
    {
        return currency;
    }


}