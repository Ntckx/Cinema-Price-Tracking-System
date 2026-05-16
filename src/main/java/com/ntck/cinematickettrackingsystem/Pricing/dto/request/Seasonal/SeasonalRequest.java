package com.ntck.cinematickettrackingsystem.Pricing.dto.request.Seasonal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeasonalRequest {
    @NotNull(message = "Seasonal type is required")
    private SeasonalType seasonalType;

    @DecimalMin(value = "0.0", message = "Seasonal percent cannot be negative")
    private double seasonalPercent;

    @DecimalMin(value = "0.0", inclusive = false, message = "Every X baht must be greater than 0")
    private double everyXBaht;

    @DecimalMin(value = "0.0", inclusive = false, message = "Discount Y baht must be greater than 0")
    private double discountYBaht;

}
