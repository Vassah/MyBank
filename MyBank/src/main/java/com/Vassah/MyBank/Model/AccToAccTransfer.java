package com.Vassah.MyBank.Model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccToAccTransfer {

    private Long senderNumber;

    private Long recieverNumber;

    private BigDecimal amount;
    
}
