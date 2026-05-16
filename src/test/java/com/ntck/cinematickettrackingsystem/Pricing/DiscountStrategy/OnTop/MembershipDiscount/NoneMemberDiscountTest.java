package com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategy.OnTop.MembershipDiscount;

import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.MembershipDiscount.NoneMemberDiscount;
import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoneMemberDiscountTest {
    private final NoneMemberDiscount noneMemberDiscount = new NoneMemberDiscount();
    @Test
    void shouldReturnNoDiscount(){
        PricingContext pricingContext = PricingContext.builder()
                .memberTier(MemberTier.NONE)
                .build();

        double result = noneMemberDiscount.applyDiscount(200.0, pricingContext);

        assertThat(result).describedAs("None member should not receive member discount")
                .isEqualTo(200.0);
    }


    @Test
    void shouldReturnZeroWhenBasePriceIsZero() {
        PricingContext pricingContext = PricingContext.builder()
                .memberTier(MemberTier.NONE)
                .build();

        double result = noneMemberDiscount.applyDiscount(0.0, pricingContext);

        assertThat(result)
                .describedAs("Non member discount should keep zero price unchanged")
                .isEqualTo(0.0);
    }
}
