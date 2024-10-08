package com.payment.demo.controller;

import java.util.List;

import com.payment.demo.dto.RazorpayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.payment.demo.entity.PaymentHistory;
import com.payment.demo.entity.PaymentRequest;
import com.payment.demo.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/fetch/{email}")
    public ResponseEntity<List<PaymentHistory>> getUserTransacions(@PathVariable String email){
        return ResponseEntity.ok(paymentService.getByUser(email));
    }

    @PostMapping("/requests")

    public ResponseEntity<PaymentRequest> createPaymentRequest( 
        @RequestBody PaymentRequest paymentRequest) {
        PaymentRequest createdRequest = paymentService.createPaymentRequest(paymentRequest);
        return ResponseEntity.ok(createdRequest);
    }

    @PostMapping("/history")
    public ResponseEntity<PaymentHistory> createPaymentHistory(@RequestBody PaymentHistory paymentHistory) {
        PaymentHistory createdHistory = paymentService.createPaymentHistory(paymentHistory);
        return ResponseEntity.ok(createdHistory);
    }

    @PutMapping("/change")
    public ResponseEntity<PaymentHistory> changeStatus(@RequestParam Long id){
        return ResponseEntity.ok(paymentService.changeStatus(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPaymentLink(
            @RequestParam int amount,
            @RequestParam String description,
            @RequestParam String email,
            @RequestParam int id) {

        return ResponseEntity.ok().body(paymentService.createPaymentLink(amount, description, email, id).getShort_url());
    }

    // Other endpoints
    @GetMapping("/requests")
    public ResponseEntity<List<PaymentRequest>> getPaymentRequest(){
    	return ResponseEntity.ok(paymentService.getPaymentRequest());
    }
    
    @GetMapping("/history")
    public ResponseEntity<List<PaymentHistory>> getPaymentHistory(){
    	return ResponseEntity.ok(paymentService.getPaymentHistory());
    }
}

