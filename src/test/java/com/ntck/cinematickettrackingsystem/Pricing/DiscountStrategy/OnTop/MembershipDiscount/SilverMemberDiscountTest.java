package com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategy.OnTop.MembershipDiscount;

import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.MembershipDiscount.SilverMemberDiscount;
import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SilverMemberDiscountTest {
    private final SilverMemberDiscount silverMemberDiscount = new SilverMemberDiscount();

    @Test
    void shouldApplySilverMemberDiscount(){
        PricingContext pricingContext  = PricingContext.builder()
                .memberTier(MemberTier.SILVER)
                .build();

        double result = silverMemberDiscount.applyDiscount(200.0, pricingContext);

        assertThat(result).describedAs("Silver member should receive 10 percent discount")
                .isEqualTo(180.0);
    }

    @Test
    void shouldReturnZeroWhenBasePriceIsZero() {
        PricingContext pricingContext = PricingContext.builder().build();

        double result = silverMemberDiscount.applyDiscount(0.0, pricingContext);

        assertThat(result)
                .describedAs("Final price should remain 0 when base price is 0")
                .isEqualTo(0.0);
    }

}
