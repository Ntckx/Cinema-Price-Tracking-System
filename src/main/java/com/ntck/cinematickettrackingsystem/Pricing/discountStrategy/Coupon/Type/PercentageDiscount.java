package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Coupon.Type;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Coupon.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PercentageDiscount extends Coupon {

    @Override
    public double applyDiscount(double basePrice, PricingContext pricingContext){
        if (pricingContext.getCouponValue() < 0 || pricingContext.getCouponValue() > 100) {
            throw new DiscountOutOfBoundsException("Percentage discount must be between 0 and 100");
        }
        double finalPrice = (100.0-pricingContext.getCouponValue()) * basePrice/100;
        if(finalPrice <= 0){
            return 0;
        }
        return preventNegative(finalPrice);

    }
}
