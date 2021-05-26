package com.example.restservices.repository;

import com.example.restservices.models.Transactions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    @Query("SELECT t FROM Transactions t ORDER BY t.id DESC")
    List<Transactions> getLastTransaction(Pageable pageable);
}
