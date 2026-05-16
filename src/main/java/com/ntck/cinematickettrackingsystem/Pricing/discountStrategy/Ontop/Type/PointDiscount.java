package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.OnTop;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PointDiscount extends OnTop {
    @Override
    public double applyDiscount(double basePrice, PricingContext pricingContext){
//        1 point is 1 baht but cannot discount over 20% of the basePrice
        if(pricingContext.getPointsUsed() < 0){
            throw new DiscountOutOfBoundsException("Point used cannot be less than 0");
        }

        double maxDiscount = basePrice * 0.2;

        double actualDiscount = Math.min(pricingContext.getPointsUsed(),maxDiscount);

        double finalPrice = basePrice - actualDiscount;

        return preventNegative(finalPrice);
    }

}
