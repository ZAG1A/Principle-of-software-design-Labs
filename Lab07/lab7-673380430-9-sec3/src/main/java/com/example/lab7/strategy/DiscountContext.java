package com.example.lab7.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DiscountContext {

    private final Map<String, DiscountStrategy> strategies;

    // Spring จะฉีด Strategy ทั้งหมดเข้ามาใน Map อัตโนมัติ (Dependency Injection)
    public DiscountContext(Map<String, DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    public double calculateFinalPrice(String discountType, double price) {
        if (discountType == null) {
            return price;
        }
        DiscountStrategy strategy = strategies.getOrDefault(discountType.toUpperCase(), strategies.get("NONE"));
        if (strategy != null) {
            return strategy.calculatePrice(price);
        }
        return price;
    }
}