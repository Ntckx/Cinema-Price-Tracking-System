package com.ntck.cinematickettrackingsystem.Pricing.Context;

import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class PricingContext {
    private UUID movieRoundId;

    private double originalPrice;

    private double currentPrice;

    private double couponValue;

    private double pointsUsed;
    private MemberTier memberTier;

    private double seasonalPercent;
    private double everyXBaht;
    private double discountYBaht;
}
