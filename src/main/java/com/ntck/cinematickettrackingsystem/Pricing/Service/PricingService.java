package com.ntck.cinematickettrackingsystem.Pricing.Service;

import com.ntck.cinematickettrackingsystem.Pricing.dto.request.CalculatePriceRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.response.CalculatePriceResponse;

public interface PricingService {
    CalculatePriceResponse calculatePrice(CalculatePriceRequest request);
}
