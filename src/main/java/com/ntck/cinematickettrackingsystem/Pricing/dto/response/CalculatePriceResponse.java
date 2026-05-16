package com.ntck.cinematickettrackingsystem.Pricing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculatePriceResponse {
    private UUID movieRoundId;

    private double originalPrice;

    private double discountAmount;

    private double finalPrice;
}
