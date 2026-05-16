package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy;

import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;

public interface DiscountStrategy {
    double applyDiscount(double basePrice, PricingContext pricingContext);
}
