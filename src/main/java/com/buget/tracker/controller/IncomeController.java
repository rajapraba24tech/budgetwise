package com.buget.tracker.controller;

import com.buget.tracker.model.Income;
import com.buget.tracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/income")
@CrossOrigin(origins = "*")
public class IncomeController {

    @Autowired
    private IncomeRepository incomeRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addIncome(@RequestBody Income income) {
        income.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(incomeRepository.save(income));
    }

    @GetMapping("/user/{userId}/{year}/{month}")
    public ResponseEntity<List<Income>> getIncomeByMonth(
            @PathVariable Long userId,
            @PathVariable Integer year,
            @PathVariable Integer month) {
        return ResponseEntity.ok(
            incomeRepository.findByUserIdAndMonthAndYear(userId, month, year));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Income>> getAllIncome(@PathVariable Long userId) {
        return ResponseEntity.ok(incomeRepository.findByUserId(userId));
    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<?> updateIncome(@PathVariable Long incomeId,
            @RequestBody Income updated) {
        return incomeRepository.findById(incomeId).map(inc -> {
            inc.setAmount(updated.getAmount());
            inc.setSource(updated.getSource());
            return ResponseEntity.ok(incomeRepository.save(inc));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long incomeId) {
        incomeRepository.deleteById(incomeId);
        return ResponseEntity.ok("Deleted!");
    }
}