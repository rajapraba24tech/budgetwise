package com.buget.tracker.controller;

import com.buget.tracker.model.Bill;
import com.buget.tracker.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addBill(@RequestBody Bill bill) {
        return ResponseEntity.ok(billRepository.save(bill));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bill>> getBills(@PathVariable Long userId) {
        return ResponseEntity.ok(billRepository.findByUserId(userId));
    }

    @DeleteMapping("/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        billRepository.deleteById(billId);
        return ResponseEntity.ok("Deleted!");
    }
}