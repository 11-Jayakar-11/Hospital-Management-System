package com.cognizant.HMS.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cognizant.HMS.entity.Billing;
import com.cognizant.HMS.entity.BillingItem;
import com.cognizant.HMS.exception.BillNotFoundException;
import com.cognizant.HMS.exception.InvalidServiceException;
import com.cognizant.HMS.repository.*;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    private static final Map<String, Double> PRICE_MAP = Map.of(
        "Consultation", 500.0,
        "Medication", 1000.0,
        "Lab Test", 750.0,
        "X-Ray", 1200.0,
        "ECG", 600.0
    );

    public Billing generateBill(Long appointmentId, String patientName, String patientContact, String patientAddress,
                                LocalDate appointmentDate, List<String> serviceNames) {

        List<BillingItem> serviceList = serviceNames.stream()
                .map(service -> {
                    Double price = PRICE_MAP.get(service);
                    if (price == null) {
                        throw new InvalidServiceException("Service not recognized: " + service);
                    }
                    return new BillingItem(service, price);
                })
                .collect(Collectors.toList());

        Billing bill = new Billing(appointmentId, patientName, patientContact, patientAddress, appointmentDate, serviceList);
        return billingRepository.save(bill);
    }

    public Billing getBillByAppointmentId(Long appointmentId) {
        return billingRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new BillNotFoundException("Bill not found for appointment ID: " + appointmentId));
    }

    public List<Billing> getAllBills() {
        return billingRepository.findAll();
    }

    public Billing getLatestBill() {
        List<Billing> list = BillingRepository.findLatestBills(PageRequest.of(0, 1));
        return list.isEmpty() ? null : list.get(0);
    }
}
