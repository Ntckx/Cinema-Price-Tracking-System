package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Coupon.Type;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Coupon.Coupon;
import org.springframework.stereotype.Component;

@Component
public class FixedDiscount extends Coupon {
    @Override
    public double applyDiscount(double basePrice, PricingContext pricingContext){
        if (pricingContext.getCouponValue() < 0) {
            throw new DiscountOutOfBoundsException("Fixed discount amount cannot be negative");
        }

        if (pricingContext.getCouponValue() > basePrice) {
            throw new DiscountOutOfBoundsException("Fixed discount amount cannot be greater than base price");
        }
        return pricingContext.updateCurrentPrice(preventNegative(basePrice - pricingContext.getCouponValue()));
    }

}
