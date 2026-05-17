package com.ntck.cinematickettrackingsystem.Pricing.Controller;

import com.ntck.cinematickettrackingsystem.Pricing.Service.PricingService;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.CalculatePriceRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.Coupon.CouponRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.response.CalculatePriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PricingController.class)
public class PricingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PricingService pricingService;

    @Test
    void givenFixedCoupon_whenCalculatePrice_thenReturnStatus200WithFinalPrice() throws Exception {

        UUID movieRoundId = UUID.randomUUID();

        CouponRequest couponRequest = new CouponRequest();
        couponRequest.setDiscountAmount(30.0);

        CalculatePriceRequest calculatePriceRequest = new CalculatePriceRequest();
        calculatePriceRequest.setMovieRoundId(movieRoundId);
        calculatePriceRequest.setCouponRequest(couponRequest);

        CalculatePriceResponse calculatePriceResponse = CalculatePriceResponse.builder()
                .movieRoundId(movieRoundId)
                .originalPrice(200.0)
                .finalPrice(170.0)
                .build();

        when(pricingService.calculatePrice(eq(calculatePriceRequest)))
                .thenReturn(calculatePriceResponse);

        mockMvc.perform(post("/api/pricing/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(calculatePriceRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieRoundId").value(movieRoundId.toString()))
                .andExpect(jsonPath("$.originalPrice").value(200.0))
                .andExpect(jsonPath("$.finalPrice").value(170.0));
    }

    @Test
    void givenBlankRequest_whenCalculatePrice_thenReturnStatus400withRFC7807() throws Exception{
            mockMvc.perform(post("/api/pricing/calculate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.status").value(400))
                    .andExpect(jsonPath("$.title").exists());
    }
}
