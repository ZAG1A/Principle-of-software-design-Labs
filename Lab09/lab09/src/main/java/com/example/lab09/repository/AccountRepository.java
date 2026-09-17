package com.example.lab09.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab09.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}