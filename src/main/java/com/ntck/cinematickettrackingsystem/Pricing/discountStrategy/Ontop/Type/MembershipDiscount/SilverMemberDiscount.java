package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.MembershipDiscount;

import org.springframework.stereotype.Component;

@Component
public class SilverMemberDiscount extends MemberDiscount{

    @Override
    protected double getDiscountBy(){
        // SilverMember get discount 10
        return 10;
    }
}
