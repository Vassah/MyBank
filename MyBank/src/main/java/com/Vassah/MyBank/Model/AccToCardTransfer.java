package com.Vassah.MyBank.Model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccToCardTransfer {

    private Long senderAccNumber;

    private String recieverCardNumber;

    private BigDecimal amount;
    
}
