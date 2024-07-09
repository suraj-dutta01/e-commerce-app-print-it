package com.printit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.printit.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
