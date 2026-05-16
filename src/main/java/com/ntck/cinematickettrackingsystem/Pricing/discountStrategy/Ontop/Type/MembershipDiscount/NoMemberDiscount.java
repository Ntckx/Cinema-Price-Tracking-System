package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.MembershipDiscount;

import org.springframework.stereotype.Component;

@Component
public class NoMemberDiscount extends MemberDiscount {
    protected double getDiscountBy(){
        return 0;
    }
}
