package com.buget.tracker.controller;

import com.buget.tracker.model.BudgetPlan;
import com.buget.tracker.repository.BudgetPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin(origins = "*")
public class BudgetPlanController {

    @Autowired
    private BudgetPlanRepository budgetPlanRepository;

    // Get user's budget plan
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBudget(@PathVariable Long userId) {
        BudgetPlan plan = budgetPlanRepository.findByUserId(userId)
            .orElseGet(() -> {
                // Default plan create பண்ணு
                BudgetPlan def = new BudgetPlan();
                def.setUserId(userId);
                return budgetPlanRepository.save(def);
            });
        return ResponseEntity.ok(plan);
    }

    // Save or Update budget plan
    @PostMapping("/save")
    public ResponseEntity<?> saveBudget(@RequestBody BudgetPlan plan) {
        double total = plan.getMonthlyExpensePct()
                     + plan.getWeeklyExpensePct()
                     + plan.getWantsPct()
                     + plan.getSavingsPct()
                     + plan.getBufferPct();

        if (Math.abs(total - 100.0) > 0.01) {
            return ResponseEntity.badRequest()
                .body("Total must be 100%! Current total: " + total + "%");
        }

        BudgetPlan existing = budgetPlanRepository
            .findByUserId(plan.getUserId())
            .orElse(plan);

        existing.setUserId(plan.getUserId());
        existing.setMonthlyExpensePct(plan.getMonthlyExpensePct());
        existing.setWeeklyExpensePct(plan.getWeeklyExpensePct());
        existing.setWantsPct(plan.getWantsPct());
        existing.setSavingsPct(plan.getSavingsPct());
        existing.setBufferPct(plan.getBufferPct());

        return ResponseEntity.ok(budgetPlanRepository.save(existing));
    }
}