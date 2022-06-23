package com.Vassah.MyBank.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Data 
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long number;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    private Currency currency;

    private BigDecimal balance;
    
    @OneToMany(mappedBy = "id")
    private List<Transaction> transactions;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "card_id")
    private Card card;

    private AccountStatus status;

    private BigDecimal balanceLimit = BigDecimal.valueOf(0);

    public void addTransaction(Transaction transaction)
    {
        transactions.add(transaction);
    }
}
