package com.example.lab09.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab09.model.DepositTransaction;

public interface DepositRepository extends JpaRepository<DepositTransaction, Long> {
}