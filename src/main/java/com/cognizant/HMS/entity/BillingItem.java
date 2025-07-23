package com.cognizant.HMS.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class BillingItem {

    public BillingItem(){
        
    }

    private String serviceName;

    private double price;
}
