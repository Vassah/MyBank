package com.Vassah.MyBank.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Currency;

@Entity
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long number;

    private User user;
    
    private Currency balance;
    
    private Transaction[] transactions;

    private Card card;

    private AccountStatus status;

    protected Account(){};

    


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
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return Currency return the balance
     */
    public Currency getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Currency balance) {
        this.balance = balance;
    }

    /**
     * @return Transaction[] return the transactions
     */
    public Transaction[] getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    /**
     * @return Card return the card
     */
    public Card getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * @return AccountStatus return the status
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(AccountStatus status) {
        this.status = status;
    }

}
