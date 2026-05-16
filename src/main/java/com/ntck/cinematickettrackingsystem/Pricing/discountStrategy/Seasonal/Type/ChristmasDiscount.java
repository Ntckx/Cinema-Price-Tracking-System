package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Type;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Seasonal;
import org.springframework.stereotype.Component;

@Component
public class ChristmasDiscount extends Seasonal {
    @Override
    public double applyDiscount(double basePrice, PricingContext pricingContext){

        double percent = pricingContext.getSeasonalPercent();
        if (percent < 0 || percent > 100) {
            throw new DiscountOutOfBoundsException("Christmas discount percent must be between 0 and 100");
        }

        double discountPrice = basePrice * percent/100;

        double maxDiscount = 0.8 * basePrice;
        double actualDiscount = Math.min(discountPrice,maxDiscount);
        double finalPrice = basePrice - actualDiscount;
        return preventNegative(finalPrice);
    }
}
