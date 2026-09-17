package com.example.lab09.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lab09.model.Account;
import com.example.lab09.model.DepositTransaction;
import com.example.lab09.repository.AccountRepository;
import com.example.lab09.repository.DepositRepository;

@Service
public class DepositService {
    private final AccountRepository accountRepository;
    private final DepositRepository depositRepository;

    public DepositService(AccountRepository accountRepository, DepositRepository depositRepository) {
        this.accountRepository = accountRepository;
        this.depositRepository = depositRepository;
    }

    @Transactional
    public void deposit(Long accountId, Double amount) {
        // 1. ค้นหา Account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));

        // 2. เพิ่มจำนวนเงินเข้า balance แล้วบันทึก Account
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        // 3. สร้าง DepositTransaction ผูกกับ Account นั้น แล้วบันทึก
        DepositTransaction deposit = new DepositTransaction();
        deposit.setAmount(amount);
        deposit.setAccount(account);
        depositRepository.save(deposit);
        throw new RuntimeException("Test Rollback");
    }
}