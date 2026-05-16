package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Type;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Seasonal;
import org.springframework.stereotype.Component;

@Component
public class HappyHour extends Seasonal {
    public double applyDiscount(double basePrice, PricingContext pricingContext){
        if(pricingContext.getEveryXBaht() <= 0){
            throw new DiscountOutOfBoundsException("Discount threshold must be > 0");
        }

        if(pricingContext.getDiscountYBaht() <= 0){
            throw new DiscountOutOfBoundsException("Discount Y must be > 0");
        }

        double discountTime = Math.floor((basePrice)/(pricingContext.getEveryXBaht()));
        double totalDiscount = discountTime * pricingContext.getDiscountYBaht();

        return preventNegative(basePrice - totalDiscount);
    }
}
