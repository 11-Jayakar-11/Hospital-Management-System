package com.cognizant.HMS.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "billing")
@Data
@NoArgsConstructor
public class Billing {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // It will create unique identity for billId
    private Long billId;

    private Long appointmentId;
    private String patientName;
    private String patientContact;
    private String patientAddress;
    private LocalDate appointmentDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "billing_items", joinColumns =  @JoinColumn(name = "billId"))
    private List<BillingItem> services;

    private double totalAmount;

    public Billing(Long appointmentId, String patientName, String patientContact, String patientAddress, LocalDate appointmentDate, List<BillingItem> services){
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.patientContact = patientContact;
        this.patientAddress = patientAddress;
        this.appointmentDate = appointmentDate;
        this.services = services;
        this.totalAmount = services.stream()
        .mapToDouble(BillingItem::getPrice)
        .sum();
    } 

}
