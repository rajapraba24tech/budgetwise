package com.buget.tracker.controller;

import com.buget.tracker.model.Expense;
import com.buget.tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestBody Expense expense) {
        if (expense.getExpenseDate() == null) {
            expense.setExpenseDate(LocalDate.now());
        }
        expense.setMonth(expense.getExpenseDate().getMonthValue());
        expense.setYear(expense.getExpenseDate().getYear());
        return ResponseEntity.ok(expenseRepository.save(expense));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getAllExpenses(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseRepository.findByUserId(userId));
    }

    @GetMapping("/user/{userId}/{year}/{month}")
    public ResponseEntity<List<Expense>> getExpensesByMonth(
            @PathVariable Long userId,
            @PathVariable Integer year,
            @PathVariable Integer month) {
        return ResponseEntity.ok(
            expenseRepository.findByUserIdAndMonthAndYear(userId, month, year));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
        return ResponseEntity.ok("Deleted!");
    }
}