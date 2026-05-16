package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.MembershipDiscount;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.OnTop;

public abstract class MemberDiscount extends OnTop {
    protected abstract double getDiscountBy();
    @Override
    public double applyDiscount(double currentPrice, PricingContext pricingContext){
        double discountRate = getDiscountBy();
        if (discountRate < 0 || discountRate > 100) {
            throw new DiscountOutOfBoundsException("Member discount rate must be between 0 and 100");
        }

        double discountAmount = currentPrice * discountRate / 100;
        double finalPrice = currentPrice - discountAmount;

        return preventNegative(finalPrice);
    }
}
