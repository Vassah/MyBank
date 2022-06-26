package com.Vassah.MyBank.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "cards")
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq")
    @SequenceGenerator(initialValue = 10000000, name = "card_seq")
    private long id;

    private int CVV;

    private String name;

    private String expiration;

    private String passwordHash;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    public String getNumber()
    {
        return String.join(" ", "1234", "5678", String.valueOf(id).substring(0, 4), String.valueOf(id).substring(4, 8));
    }

}
