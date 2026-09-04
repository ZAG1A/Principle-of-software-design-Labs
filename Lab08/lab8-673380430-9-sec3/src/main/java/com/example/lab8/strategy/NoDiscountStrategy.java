package com.example.lab8.strategy;

import org.springframework.stereotype.Component;

@Component
public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double price) {
        return price;
    }
}