package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Type;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Seasonal;
import org.springframework.stereotype.Component;

@Component
public class HalloweenDiscount extends Seasonal {
    public double applyDiscount(double basePrice, PricingContext pricingContext){
        if (pricingContext.getCouponValue() < 0 || pricingContext.getSeasonalPercent() > 100) {
            throw new DiscountOutOfBoundsException("Halloween discount percent must be between 0 and 100");
        }
        double maxDiscount = 0.7 * basePrice;
        double discount = basePrice * pricingContext.getCouponValue()/100;
        double actualDiscount = Math.min(maxDiscount,discount);
        double finalPrice = basePrice - actualDiscount;
        return preventNegative(finalPrice);
    }

}
