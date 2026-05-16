package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop;

import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.DiscountStrategy;

public abstract class OnTop implements DiscountStrategy {
    protected double preventNegative(double price) {
        return Math.max(price, 0);
    }
}
