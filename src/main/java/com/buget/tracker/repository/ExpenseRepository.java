package com.buget.tracker.repository;

import com.buget.tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
    List<Expense> findByUserIdAndMonthAndYear(Long userId, Integer month, Integer year);
}