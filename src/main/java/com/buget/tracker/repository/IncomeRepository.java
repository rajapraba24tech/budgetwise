package com.buget.tracker.repository;

import com.buget.tracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserId(Long userId);
    List<Income> findByUserIdAndMonthAndYear(Long userId, Integer month, Integer year);
}