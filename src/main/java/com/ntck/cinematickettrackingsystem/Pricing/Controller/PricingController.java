package com.ntck.cinematickettrackingsystem.Pricing.Controller;

import com.ntck.cinematickettrackingsystem.Pricing.Service.PricingService;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.CalculatePriceRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.response.CalculatePriceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pricing")
@RequiredArgsConstructor
public class PricingController {
    private final PricingService pricingService;

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public CalculatePriceResponse calculatePrice(@RequestBody @Valid CalculatePriceRequest request){
        return pricingService.calculatePrice(request);
    }

}
