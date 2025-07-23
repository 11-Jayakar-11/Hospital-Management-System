package com.cognizant.HMS.contoller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.HMS.entity.*;
import com.cognizant.HMS.service.*;

@RestController
@RequestMapping("/api/billing")
public class Billingcontroller {
    

    @Autowired
    private BillingService billingService;

    @PostMapping("/generate")
    public Billing generateBill(@RequestBody Map<String, Object> requestData){
        Long appointmentId = Long.valueOf(requestData.get("appointmentId").toString());
        String patientName = requestData.get("patientName").toString();
        String patientContact = requestData.get("patientContact").toString();
        String patientAddress = requestData.get("patientAddress").toString();
        LocalDate appointmentDate = LocalDate.parse(requestData.get("appointmentDate").toString());

        List<String> services = (List<String>) requestData.get("services");
        
        return billingService.generateBill(appointmentId, patientName, patientContact, patientAddress, appointmentDate, services);
    }

    @GetMapping("/get/appointment/{appointmentId}")
    public Billing getBillByAppointmentId(@PathVariable Long appointmentId){
        return billingService.getBillByAppointmentId(appointmentId);
    }

    @GetMapping("/get/all")
    public List<Billing> getAllBills(){
        return billingService.getAllBills();
    }

    @GetMapping("/latest")
    public Billing getLatestBill(){
        return billingService.getLatestBill();
    }

}
