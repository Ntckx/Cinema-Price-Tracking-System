package com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategy.Coupon;

import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Coupon.Type.FixedDiscount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FixedDiscountTest {
    FixedDiscount fixedDiscount = new FixedDiscount();

    @Test
    void shouldSubtractBasePriceWithFixedAmount(){
        PricingContext pricingContext = PricingContext.builder().couponValue(50.0).build();

        double result = fixedDiscount.applyDiscount(200.0, pricingContext);

        assertThat(result).describedAs("Final price should return base price - fixed amount discount")
                .isEqualTo(150.0);
    }

    @Test
    void shouldNotReturnNegativeResult(){
        PricingContext pricingContext = PricingContext.builder().couponValue(250.0).build();

        double result = fixedDiscount.applyDiscount(200.0, pricingContext);

        assertThat(result).describedAs("Final price should not return negative price after discount")
                .isEqualTo(0);
    }
}
