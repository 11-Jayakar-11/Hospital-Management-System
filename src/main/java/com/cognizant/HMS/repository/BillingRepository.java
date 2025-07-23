package com.cognizant.HMS.repository;

import com.cognizant.HMS.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BillingRepository extends JpaRepository<Billing, Long> {

    Optional<Billing> findByAppointmentId(Long appointmentId);

    @Query("SELECT b FROM Billing b ORDER BY b.billId DESC")
    static
    List<Billing> findLatestBills(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findLatestBills'");
    }
}
