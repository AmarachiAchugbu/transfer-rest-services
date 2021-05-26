package com.example.restservices.controller;

import com.example.restservices.models.Balances;
import com.example.restservices.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BalanceController {
    @Autowired
    BalanceRepository balanceRepository;

    @GetMapping("/balance")
    public List<Balances> getAllBalances() {
        return balanceRepository.findAll();
    }

    @PostMapping("/balance")
    public Balances addBalance(@RequestBody Balances balance) {
        return balanceRepository.save(balance);
    }
}
