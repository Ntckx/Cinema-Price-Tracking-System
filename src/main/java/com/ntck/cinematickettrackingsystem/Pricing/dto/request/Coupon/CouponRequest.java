package com.ntck.cinematickettrackingsystem.Pricing.dto.request.Coupon;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponRequest {

    @NotNull(message = "Coupon type is required")
    private CouponType couponType;

    @NotNull(message = "Fixed discount amount is required")
    @Positive( message = "Fixed discount amount must be greater than 0")
    private double discountAmount;

    @Positive(message = "Percentage Discount")
    private double percentage;
}
