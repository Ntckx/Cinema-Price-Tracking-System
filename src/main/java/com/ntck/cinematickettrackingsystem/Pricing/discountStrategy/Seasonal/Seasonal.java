package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal;

import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.DiscountStrategy;

public abstract class Seasonal implements DiscountStrategy {
    protected double preventNegative(double price) {
        return Math.max(price, 0);
    }
}
