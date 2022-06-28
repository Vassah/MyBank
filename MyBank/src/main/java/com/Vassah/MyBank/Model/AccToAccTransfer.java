package com.Vassah.MyBank.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccToAccTransfer {

    private Long senderAccNumber;

    private Long recieverAccNumber;

    private BigDecimal amount;
    
}
