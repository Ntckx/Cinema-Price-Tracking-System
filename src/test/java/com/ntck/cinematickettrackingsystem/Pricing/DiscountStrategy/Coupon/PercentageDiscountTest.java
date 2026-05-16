package com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategy.Coupon;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Coupon.Type.PercentageDiscount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PercentageDiscountTest {
    PercentageDiscount percentageDiscount = new PercentageDiscount();

    @Test
    public void shouldReturnActualPriceAfterDiscountFixedPercentage(){
        PricingContext pricingContext = PricingContext.builder()
                .couponValue(30.0)
                .build();

        double result = percentageDiscount.applyDiscount(200.0, pricingContext);

        assertThat(result).describedAs("Should return result with fixed percentage discount")
                .isEqualTo(140.0);
    }

    @Test
    public void shouldThrowExceptionWhenPercentageIsAbove100(){
        PricingContext pricingContext = PricingContext.builder().couponValue(150.0)
                .build();

        assertThatThrownBy(()-> percentageDiscount.applyDiscount(200.0, pricingContext))
                .as("Fixed percentage discount should not exceeds 100")
                .isInstanceOf(DiscountOutOfBoundsException.class)
                .hasMessageContaining("Percentage discount must be between 0 and 100");
    }

    @Test
    public void shouldThrowExceptionWhenPercentageIsNegative(){
        PricingContext pricingContext = PricingContext.builder().couponValue(-30).build();

        assertThatThrownBy(()->percentageDiscount.applyDiscount(200.0, pricingContext))
                .as("Fixed percentage should be positive")
                .isInstanceOf(DiscountOutOfBoundsException.class)
                .hasMessageContaining("Percentage discount must be between 0 and 100");
    }
}
