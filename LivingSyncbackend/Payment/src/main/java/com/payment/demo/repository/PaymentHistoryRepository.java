package com.payment.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.demo.entity.PaymentHistory;

import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
    List<PaymentHistory> findByPaidBy(String paidBy);
}
