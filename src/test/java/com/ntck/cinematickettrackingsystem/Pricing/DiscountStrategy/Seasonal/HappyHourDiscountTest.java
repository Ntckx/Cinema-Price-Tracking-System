package com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategy.Seasonal;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Type.HappyHour;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HappyHourTest {

    private final HappyHour happyHour = new HappyHour();

    @Test
    void shouldSubtractYBahtForEveryXBahtSpent() {
        PricingContext pricingContext = PricingContext.builder()
                .everyXBaht(200.0)
                .discountYBaht(20.0)
                .build();

        double result = happyHour.applyDiscount(450.0, pricingContext);

        assertThat(result)
                .describedAs("Every 200 baht spent should subtract 20 baht")
                .isEqualTo(410.0);
    }

    @Test
    void shouldNotApplyDiscountWhenPriceIsBelowThreshold() {
        PricingContext pricingContext = PricingContext.builder()
                .everyXBaht(200.0)
                .discountYBaht(20.0)
                .build();

        double result = happyHour.applyDiscount(150.0, pricingContext);

        assertThat(result)
                .describedAs("No Happy Hour discount should apply when price is below threshold")
                .isEqualTo(150.0);
    }

    @Test
    void shouldApplyDiscountExactlyAtThreshold() {
        PricingContext pricingContext = PricingContext.builder()
                .everyXBaht(200.0)
                .discountYBaht(20.0)
                .build();

        double result = happyHour.applyDiscount(200.0, pricingContext);

        assertThat(result)
                .describedAs("At exactly 200 baht, Happy Hour should subtract 20 baht once")
                .isEqualTo(180.0);
    }

    @Test
    void shouldThrowWhenEveryXBahtIsZero() {
        PricingContext pricingContext = PricingContext.builder()
                .everyXBaht(0.0)
                .discountYBaht(20.0)
                .build();

        assertThatThrownBy(() -> happyHour.applyDiscount(450.0, pricingContext))
                .as("Every X baht must be greater than zero")
                .isInstanceOf(DiscountOutOfBoundsException.class)
                .hasMessageContaining("Discount threshold must be > 0");
    }

    @Test
    void shouldThrowWhenDiscountYBahtIsZero() {
        PricingContext pricingContext = PricingContext.builder()
                .everyXBaht(200.0)
                .discountYBaht(0.0)
                .build();

        assertThatThrownBy(() -> happyHour.applyDiscount(450.0, pricingContext))
                .as("Discount Y baht must be greater than zero")
                .isInstanceOf(DiscountOutOfBoundsException.class)
                .hasMessageContaining("Discount Y must be > 0");
    }
}