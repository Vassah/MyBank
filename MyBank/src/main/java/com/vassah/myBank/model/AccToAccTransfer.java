package com.vassah.myBank.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccToAccTransfer {

    private Long senderAccNumber;

    private Long recieverAccNumber;

    private BigDecimal amount;
    
}
