package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Type;

import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Seasonal;
import org.springframework.stereotype.Component;

@Component
public class ChristmasDiscount extends Seasonal {
    @Override
    public double applyDiscount(double basePrice, PricingContext pricingContext){
        double discountPrice = (100-pricingContext.getSeasonalPercent()) * basePrice/100;
        double maxDiscount = 0.8 * basePrice;
        double actualDiscount = Math.min(discountPrice,maxDiscount);
        double finalPrice = basePrice - actualDiscount;
        return preventNegative(finalPrice);
    }
}
