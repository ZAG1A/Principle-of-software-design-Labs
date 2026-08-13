package com.example.lab7.strategy;

import org.springframework.stereotype.Component;

@Component("STUDENT")
public class StudentDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.90; // ลด 10%
    }
}