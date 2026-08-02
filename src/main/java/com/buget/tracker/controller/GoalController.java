package com.buget.tracker.controller;

import com.buget.tracker.model.Goal;
import com.buget.tracker.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "*")
public class GoalController {

    @Autowired
    private GoalRepository goalRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addGoal(@RequestBody Goal goal) {
        return ResponseEntity.ok(goalRepository.save(goal));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Goal>> getGoals(@PathVariable Long userId) {
        return ResponseEntity.ok(goalRepository.findByUserId(userId));
    }

    @PutMapping("/update/{goalId}")
    public ResponseEntity<?> updateSaved(@PathVariable Long goalId,
                                          @RequestParam Double amount) {
        return goalRepository.findById(goalId).map(g -> {
            g.setSavedAmount(g.getSavedAmount() + amount);
            return ResponseEntity.ok(goalRepository.save(g));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<?> deleteGoal(@PathVariable Long goalId) {
        goalRepository.deleteById(goalId);
        return ResponseEntity.ok("Deleted!");
    }
}