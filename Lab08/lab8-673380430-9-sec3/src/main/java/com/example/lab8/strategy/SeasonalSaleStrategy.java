package com.example.lab8.strategy;

import org.springframework.stereotype.Component;

@Component
public class SeasonalSaleStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double price) {
        return price * 0.80; // ลด 20%
    }
}