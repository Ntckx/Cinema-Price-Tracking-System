package com.ntck.cinematickettrackingsystem.Pricing;

import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Coupon.Type.FixedDiscount;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Coupon.Type.PercentageDiscount;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.DiscountStrategy;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.MembershipDiscount.GoldMemberDiscount;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.MembershipDiscount.MemberDiscount;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.MembershipDiscount.NoMemberDiscount;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.MembershipDiscount.SilverMemberDiscount;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Ontop.Type.PointDiscount;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Type.ChristmasDiscount;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Type.HalloweenDiscount;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Seasonal.Type.HappyHour;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.Coupon.CouponRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.Coupon.CouponType;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.OnTop.OnTopRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.Seasonal.SeasonalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DiscountStrategyFactory {
    private final FixedDiscount fixedDiscount;
    private final PercentageDiscount percentageDiscount;
    private final NoMemberDiscount noMemberDiscount;
    private final SilverMemberDiscount silverMemberDiscount;
    private final GoldMemberDiscount goldMemberDiscount;
    private final PointDiscount pointDiscount;
    private final ChristmasDiscount christmasDiscount;
    private final HalloweenDiscount halloweenDiscount;
    private final HappyHour happyHour;

    public DiscountStrategy buildCoupon(CouponRequest couponRequest) {
        if (couponRequest == null) {
            return null;
        }
        return switch (couponRequest.getCouponType()) {
            case FIXED_AMOUNT -> fixedDiscount;
            case PERCENTAGE -> percentageDiscount;
        };
    }

    public DiscountStrategy buildOnTop(OnTopRequest onTopRequest) {
        if (onTopRequest == null) {
            return null;
        }
        return switch (onTopRequest.getOnTopType()) {
            case MEMBER_TYPE -> switch (onTopRequest.getMemberTier()){
                case NONE -> noMemberDiscount;
                case SILVER -> silverMemberDiscount;
                case GOLD ->  goldMemberDiscount;
            };
            case POINTS_REDEMPTION -> pointDiscount;
        };
    }

    public DiscountStrategy buildSeasonal(SeasonalRequest seasonalRequest){
        if(seasonalRequest == null){
            return null;
        }
        return switch (seasonalRequest.getSeasonalType()){
            case CHRISTMAS -> christmasDiscount;
            case HALLOWEEN -> halloweenDiscount;
            case HAPPY_HOUR -> happyHour;
        };
    }

}
