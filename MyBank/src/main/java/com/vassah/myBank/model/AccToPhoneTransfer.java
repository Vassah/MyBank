package com.vassah.myBank.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccToPhoneTransfer {
    
    private Long senderAccNumber;
    
    private String recieverPhoneNumber;
    
    private BigDecimal amount;
}
