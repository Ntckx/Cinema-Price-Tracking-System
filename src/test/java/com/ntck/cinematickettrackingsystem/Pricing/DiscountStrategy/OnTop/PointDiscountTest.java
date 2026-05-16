package com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategy.OnTop;

import com.ntck.cinematickettrackingsystem.GlobalException.DiscountOutOfBoundsException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.PointDiscount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PointDiscountTest {
    private final PointDiscount pointDiscount = new PointDiscount();

    @Test
    void shouldApplyPointsDiscount(){
        PricingContext pricingContext = PricingContext.builder().pointsUsed(30.0).build();

        double result = pointDiscount.applyDiscount(200.0, pricingContext);

        assertThat(result).describedAs("30 points should discount 30 THB")
                .isEqualTo(170.0);
    }

    @Test
    void shouldCapPointAtTwentyPercentOfPrice(){
        PricingContext pricingContext = PricingContext.builder().pointsUsed(100.0).build();

        double result = pointDiscount.applyDiscount(200.0, pricingContext);

        assertThat(result).describedAs("Points discount should not exceed 20 percent of current price")
                .isEqualTo(160.0);
    }

    @Test
    void shouldThrowExceptionWhenPointNegative(){
        PricingContext pricingContext = PricingContext.builder().pointsUsed(-10.0).build();

        assertThatThrownBy(() -> pointDiscount.applyDiscount(200.0, pricingContext))
                .as("Points used should not be negative")
                .isInstanceOf(DiscountOutOfBoundsException.class)
                .hasMessageContaining("Points used cannot be negative");
    }

}
