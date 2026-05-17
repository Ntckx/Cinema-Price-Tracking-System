package com.ntck.cinematickettrackingsystem.Pricing.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculatePriceResponse {
    private UUID movieRoundId;

    private double originalPrice;

    private double discountAmount;

    private double finalPrice;
}
