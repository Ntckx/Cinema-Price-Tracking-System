package com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategy.Seasonal;

import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Type.ChristmasDiscount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    }
}
