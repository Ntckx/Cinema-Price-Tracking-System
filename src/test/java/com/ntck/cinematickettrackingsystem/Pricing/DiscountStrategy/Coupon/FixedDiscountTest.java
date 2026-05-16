package com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategy.Coupon;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Coupon.Type.FixedDiscount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void shouldReturnZeroFixedDiscountIsGreaterThanBasePrice(){
        PricingContext pricingContext = PricingContext.builder().couponValue(250.0).build();
        double result = fixedDiscount.applyDiscount(200.0,pricingContext);
        assertThat(result)
                .describedAs("Result should return 0 when fixed discount is greater than base price")
                .isEqualTo(0.0);
    }

    @Test
    void shouldThrownWhenFixedDiscountIsNegative(){
        PricingContext pricingContext = PricingContext.builder().couponValue(-30.0).build();

        assertThatThrownBy(()-> fixedDiscount.applyDiscount(200.0, pricingContext))
                .as("Fixed discount should not be negative")
                .isInstanceOf(DiscountOutOfBoundsException.class)
                .hasMessageContaining("Fixed discount amount cannot be negative");
    }
}
