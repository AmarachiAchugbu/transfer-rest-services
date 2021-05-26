package com.example.restservices.repository;

import com.example.restservices.models.Balances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balances, Long> {

}
