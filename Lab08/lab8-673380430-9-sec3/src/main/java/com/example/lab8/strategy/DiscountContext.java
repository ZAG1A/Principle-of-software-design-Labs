package com.example.lab8.strategy;

import org.springframework.stereotype.Component;

@Component
public class DiscountContext {

    private final NoDiscountStrategy noDiscountStrategy;
    private final MemberDiscountStrategy memberDiscountStrategy;
    private final SeasonalSaleStrategy seasonalSaleStrategy;

    public DiscountContext(NoDiscountStrategy noDiscountStrategy,
                           MemberDiscountStrategy memberDiscountStrategy,
                           SeasonalSaleStrategy seasonalSaleStrategy) {
        this.noDiscountStrategy = noDiscountStrategy;
        this.memberDiscountStrategy = memberDiscountStrategy;
        this.seasonalSaleStrategy = seasonalSaleStrategy;
    }

    public double calculateFinalPrice(String discountType, Double price) {
        if (price == null) return 0.0;
        if (discountType == null) return price;

        switch (discountType.toUpperCase()) {
            case "MEMBER":
                return memberDiscountStrategy.calculateDiscount(price);
            case "SEASONAL":
                return seasonalSaleStrategy.calculateDiscount(price);
            default:
                return noDiscountStrategy.calculateDiscount(price);
        }
    }
}