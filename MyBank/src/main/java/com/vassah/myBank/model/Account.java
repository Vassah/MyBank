package com.vassah.myBank.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_seq")
    @SequenceGenerator(name = "acc_seq", initialValue = 1000000000)
    private long number;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Currency currency;

    private BigDecimal balance;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "senderAccountNumber")
    private Set<Transaction> sendTransactions;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recieverAccountNumber")
    private Set<Transaction> recieveTransactions;

    @OneToOne(fetch = FetchType.EAGER) //, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "card_id")
    private Card card;

    private AccountStatus status;

    private BigDecimal balanceLimit = BigDecimal.valueOf(0);

    private int multiplicator;

    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }

    @Override
    public Long getId()
    {
        return number;
    }

    public void addSendTransaction(Transaction transaction) {
        sendTransactions.add(transaction);
    }

    public void addRecieveTransaction(Transaction transaction) {
        recieveTransactions.add(transaction);
    }

    public Set<Transaction> getTransactions()
    {
        var result = new HashSet<Transaction>(sendTransactions);
        recieveTransactions.forEach((Transaction tr) -> sendTransactions.add(tr));
        return result;
    }
}
