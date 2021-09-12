package com.its.RxVsRsocket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardEntity {
    private String issuingNetwork;
    private String cardNumber;
    private String name;
    private String address;
    private String country;
    private String cvv;
    private String expiryDate;
}
