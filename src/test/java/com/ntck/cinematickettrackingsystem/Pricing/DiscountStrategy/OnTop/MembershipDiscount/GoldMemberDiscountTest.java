package com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategy.OnTop.MembershipDiscount;

import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.MembershipDiscount.GoldMemberDiscount;
import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GoldMemberDiscountTest {
    GoldMemberDiscount goldMemberDiscount = new GoldMemberDiscount();

    @Test
    void shouldApplyGoldMemberDiscount() {
        PricingContext pricingContext = PricingContext.builder().memberTier(MemberTier.GOLD)
                .build();

        double result = goldMemberDiscount.applyDiscount(200.0, pricingContext);

        assertThat(result).describedAs("Gold member tier should receive 20 percent discount")
                .isEqualTo(160.0);
    }


    @Test
    void shouldReturnZeroWhenBasePriceIsZero() {
        PricingContext pricingContext = PricingContext.builder()
                .memberTier(MemberTier.GOLD)
                .build();

        double result = goldMemberDiscount.applyDiscount(0.0, pricingContext);

        assertThat(result)
                .describedAs("Final price should remain 0 when base price is 0")
                .isEqualTo(0.0);
    }

}
