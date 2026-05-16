package com.ntck.cinematickettrackingsystem.Pricing.dto.request;

import com.ntck.cinematickettrackingsystem.Pricing.dto.request.Coupon.CouponRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.OnTop.OnTopRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.Seasonal.SeasonalRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class CalculatePriceRequest {
    @NotNull(message = "Movie round id is required")
    private UUID movieRoundId;

    private CouponRequest couponRequest;
    private OnTopRequest onTopRequest;
    private SeasonalRequest seasonalRequest;
}
