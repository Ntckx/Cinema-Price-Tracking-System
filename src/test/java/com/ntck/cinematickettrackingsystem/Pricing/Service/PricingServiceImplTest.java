package com.ntck.cinematickettrackingsystem.Pricing.Service;

import com.ntck.cinematickettrackingsystem.Bookings.MovieRoundRepository;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategyFactory;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.DiscountStrategy;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.CalculatePriceRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.Coupon.CouponRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.OnTop.OnTopRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.Seasonal.SeasonalRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.response.CalculatePriceResponse;
import com.ntck.cinematickettrackingsystem.models.MovieRound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricingServiceImplTest {

    @Mock
    private MovieRoundRepository movieRoundRepository;

    @Mock
    private DiscountStrategyFactory discountStrategyFactory;

    @Mock
    private DiscountStrategy couponStrategy;

    @Mock
    private DiscountStrategy onTopStrategy;

    @Mock
    private DiscountStrategy seasonalStrategy;

    @InjectMocks
    private PricingServiceImpl pricingService;


    @Test
    void shouldCalculatePriceWithCouponOnTopAndSeasonalDiscounts(){

        UUID movieRoundId = UUID.randomUUID();

        MovieRound movieRound = MovieRound.builder()
                .id(movieRoundId)
                .basePrice(200.0)
                .build();

        CouponRequest couponRequest = new CouponRequest();
        OnTopRequest onTopRequest = new OnTopRequest();
        SeasonalRequest seasonalRequest = new SeasonalRequest();

        couponRequest.setDiscountAmount(30.0);

        onTopRequest.setPoints(30);

        seasonalRequest.setSeasonalPercent(30.0);



        CalculatePriceRequest calculatePriceRequest = new CalculatePriceRequest();

        calculatePriceRequest.setMovieRoundId(movieRoundId);
        calculatePriceRequest.setCouponRequest(couponRequest);
        calculatePriceRequest.setOnTopRequest(onTopRequest);
        calculatePriceRequest.setSeasonalRequest(seasonalRequest);

        when(movieRoundRepository.findById(movieRoundId))
                .thenReturn(java.util.Optional.of(movieRound));

        when(discountStrategyFactory.buildCoupon(couponRequest))
                .thenReturn(couponStrategy);

        when(discountStrategyFactory.buildOnTop(onTopRequest))
                .thenReturn(onTopStrategy);

        when(discountStrategyFactory.buildSeasonal(seasonalRequest))
                .thenReturn(seasonalStrategy);

        when(couponStrategy.applyDiscount(
                eq(200.0),
                argThat(context ->
                        context.getMovieRoundId().equals(movieRoundId)
                                && context.getOriginalPrice() == 200.0
                                && context.getCurrentPrice() == 200.0
                                && context.getCouponValue() == 30.0
                                && context.getPointsUsed() == 30.0
                                && context.getSeasonalPercent() == 30.0
                )
        )).thenReturn(170.0);

        when(onTopStrategy.applyDiscount(
                eq(170.0),
                argThat(context ->
                        context.getMovieRoundId().equals(movieRoundId)
                                && context.getOriginalPrice() == 200.0
                                && context.getCurrentPrice() == 170.0
                                && context.getPointsUsed() == 30.0
                )
        )).thenReturn(140.0);

        when(seasonalStrategy.applyDiscount(
                eq(140.0),
                argThat(context ->
                        context.getMovieRoundId().equals(movieRoundId)
                                && context.getOriginalPrice() == 200.0
                                && context.getCurrentPrice() == 140.0
                                && context.getSeasonalPercent() == 30.0
                )
        )).thenReturn(98.0);

        CalculatePriceResponse calculatePriceResponse = pricingService.calculatePrice(calculatePriceRequest);

        assertThat(calculatePriceResponse.getMovieRoundId()).isEqualTo(movieRoundId);
        assertThat(calculatePriceResponse.getOriginalPrice()).isEqualTo(200.0);
        assertThat(calculatePriceResponse.getDiscountAmount()).isEqualTo(102.0);
        assertThat(calculatePriceResponse.getFinalPrice()).isEqualTo(98.0);

        InOrder inOrder = inOrder(couponStrategy, onTopStrategy, seasonalStrategy);

        inOrder.verify(couponStrategy).applyDiscount(
                eq(200.0),
                argThat(context ->
                        context.getMovieRoundId().equals(movieRoundId)
                                && context.getOriginalPrice() == 200.0
                                && context.getCouponValue() == 30.0
                                && context.getPointsUsed() == 30.0
                                && context.getSeasonalPercent() == 30.0
                )
        );

        inOrder.verify(onTopStrategy).applyDiscount(
                eq(170.0),
                argThat(context ->
                        context.getMovieRoundId().equals(movieRoundId)
                                && context.getOriginalPrice() == 200.0
                                && context.getPointsUsed() == 30.0
                                && context.getSeasonalPercent() == 30.0
                )
        );

        inOrder.verify(seasonalStrategy).applyDiscount(
                eq(140.0),
                argThat(context ->
                        context.getMovieRoundId().equals(movieRoundId)
                                && context.getOriginalPrice() == 200.0
                                && context.getSeasonalPercent() == 30.0
                )
        );
    }
}
