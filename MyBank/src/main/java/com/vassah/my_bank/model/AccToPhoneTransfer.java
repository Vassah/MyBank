package com.vassah.my_bank.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccToPhoneTransfer {
    
    private Long senderAccNumber;
    
    private String recieverPhoneNumber;
    
    private BigDecimal amount;
}
