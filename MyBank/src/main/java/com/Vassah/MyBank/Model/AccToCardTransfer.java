package com.Vassah.MyBank.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccToCardTransfer {

    private Long senderAccNumber;

    private String recieverCardNumber;

    private BigDecimal amount;
    
}
