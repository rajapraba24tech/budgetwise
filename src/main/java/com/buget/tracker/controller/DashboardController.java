package com.buget.tracker.controller;

import com.buget.tracker.model.Expense;
import com.buget.tracker.model.Income;
import com.buget.tracker.repository.ExpenseRepository;
import com.buget.tracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @GetMapping("/summary/{userId}/{year}/{month}")
    public ResponseEntity<?> getSummary(
            @PathVariable Long userId,
            @PathVariable Integer year,
            @PathVariable Integer month) {
        return ResponseEntity.ok(buildSummary(userId, month, year));
    }

    @GetMapping("/compare/{userId}")
    public ResponseEntity<?> compare(
            @PathVariable Long userId,
            @RequestParam Integer fromMonth,
            @RequestParam Integer fromYear,
            @RequestParam Integer toMonth,
            @RequestParam Integer toYear) {

        Map<String, Object> from = buildSummary(userId, fromMonth, fromYear);
        Map<String, Object> to   = buildSummary(userId, toMonth, toYear);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("from", from);
        result.put("to", to);
        result.put("incomeGrowthPct",
            growth((Double) from.get("totalIncome"), (Double) to.get("totalIncome")));
        result.put("spentGrowthPct",
            growth((Double) from.get("totalSpent"), (Double) to.get("totalSpent")));
        result.put("savingsGrowthPct",
            growth((Double) from.get("savings"), (Double) to.get("savings")));

        return ResponseEntity.ok(result);
    }

    @GetMapping("/months/{userId}")
    public ResponseEntity<?> getAvailableMonths(@PathVariable Long userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        List<Income>  incomes  = incomeRepository.findByUserId(userId);

        Set<String> monthKeys = new TreeSet<>(Comparator.reverseOrder());
        for (Expense e : expenses) {
            if (e.getYear() != null && e.getMonth() != null) {
                monthKeys.add(e.getYear() + "-" + String.format("%02d", e.getMonth()));
            }
        }
        for (Income i : incomes) {
            if (i.getYear() != null && i.getMonth() != null) {
                monthKeys.add(i.getYear() + "-" + String.format("%02d", i.getMonth()));
            }
        }

        return ResponseEntity.ok(monthKeys);
    }

    private Map<String, Object> buildSummary(Long userId, Integer month, Integer year) {
        List<Expense> expenses = expenseRepository.findByUserIdAndMonthAndYear(userId, month, year);
        List<Income>  incomes  = incomeRepository.findByUserIdAndMonthAndYear(userId, month, year);

        double totalIncome = 0;
        for (Income i : incomes) totalIncome += i.getAmount();

        double totalSpent = 0;
        Map<String, Double> categorySpent = new LinkedHashMap<>();
        for (String cat : new String[]{"MONTHLY", "WEEKLY", "WANTS", "SAVINGS", "BUFFER"}) {
            categorySpent.put(cat, 0.0);
        }
        for (Expense e : expenses) {
            totalSpent += e.getAmount();
            categorySpent.merge(e.getCategoryType(), e.getAmount(), Double::sum);
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("month", month);
        result.put("year", year);
        result.put("totalIncome", totalIncome);
        result.put("totalSpent", totalSpent);
        result.put("balance", totalIncome - totalSpent);
        result.put("savings", categorySpent.get("SAVINGS"));
        result.put("categorySpent", categorySpent);
        result.put("expenseCount", expenses.size());
        result.put("incomeCount", incomes.size());

        return result;
    }

    private Double growth(Double from, Double to) {
        if (from == null || from == 0) return (to != null && to > 0) ? 100.0 : 0.0;
        return Math.round(((to - from) / from) * 10000.0) / 100.0;
    }
}