package com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategy.Seasonal;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Type.ChristmasDiscount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChristmasDiscountTest {
    private final ChristmasDiscount christmasDiscount = new ChristmasDiscount();

    @Test
    void shouldReturnPriceAfterChristmasDiscount(){
        PricingContext pricingContext = PricingContext.builder().seasonalPercent(50.0).build();

        double result = christmasDiscount.applyDiscount(200.0, pricingContext);

        assertThat(result).describedAs("Should return return 50 % discount for christmas sale")
                .isEqualTo(100.0);
    }

    @Test
    void shouldReturnSamePriceWhenChristmasDiscount(){
        PricingContext pricingContext = PricingContext.builder()
                .seasonalPercent(0.0)
                .build();

        double result = christmasDiscount.applyDiscount(200.0,pricingContext);

        assertThat(result).describedAs("Zero percent Christmas discount should not change price")
                .isEqualTo(200.0);
    }


    @Test
    void shouldCapChristmasDiscountAtEightyPercent() {
        PricingContext pricingContext = PricingContext.builder()
                .seasonalPercent(100.0)
                .build();

        double result = christmasDiscount.applyDiscount(200.0, pricingContext);

        assertThat(result)
                .describedAs("Christmas discount should be capped at 80 percent, so customer still pays 20 percent")
                .isEqualTo(40.0);
    }

    @Test
    void shouldThrowWhenChristmasDiscountIsNegative() {
        PricingContext pricingContext = PricingContext.builder()
                .seasonalPercent(-10.0)
                .build();

        assertThatThrownBy(() -> christmasDiscount.applyDiscount(200.0, pricingContext))
                .as("Christmas discount percent should not be negative")
                .isInstanceOf(DiscountOutOfBoundsException.class)
                .hasMessageContaining("Christmas discount percent must be between 0 and 100");
    }

    @Test
    void shouldThrowWhenChristmasDiscountIsGreaterThanOneHundred() {
        PricingContext pricingContext = PricingContext.builder()
                .seasonalPercent(120.0)
                .build();

        assertThatThrownBy(() -> christmasDiscount.applyDiscount(200.0, pricingContext))
                .as("Christmas discount percent should not exceed 100")
                .isInstanceOf(DiscountOutOfBoundsException.class)
                .hasMessageContaining("Christmas discount percent must be between 0 and 100");
    }
}
