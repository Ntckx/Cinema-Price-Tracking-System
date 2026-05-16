package com.ntck.cinematickettrackingsystem.Pricing.Service;

import com.ntck.cinematickettrackingsystem.Bookings.MovieRoundRepository;
import com.ntck.cinematickettrackingsystem.GlobalException.MovieRoundNotFoundException;
import com.ntck.cinematickettrackingsystem.Pricing.Context.PricingContext;
import com.ntck.cinematickettrackingsystem.Pricing.DiscountStrategyFactory;
import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.DiscountStrategy;
import com.ntck.cinematickettrackingsystem.Pricing.dto.request.CalculatePriceRequest;
import com.ntck.cinematickettrackingsystem.Pricing.dto.response.CalculatePriceResponse;
import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import com.ntck.cinematickettrackingsystem.models.MovieRound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PricingServiceImpl implements PricingService {
    private final MovieRoundRepository movieRoundRepository;
    private final DiscountStrategyFactory discountStrategyFactory;

    @Override
    public CalculatePriceResponse calculatePrice(CalculatePriceRequest request) {
//        Check if movieRoundExist
        MovieRound movieRound = movieRoundRepository.findById(request.getMovieRoundId())
                .orElseThrow(() -> new MovieRoundNotFoundException(request.getMovieRoundId()));

//        Get Based Price
        double basePrice = movieRound.getBasePrice();
        PricingContext pricingContext = PricingContext.builder()
                .movieRoundId(movieRound.getId())
                .originalPrice(basePrice)
                .currentPrice(basePrice)
                .couponValue(request.getCouponRequest() == null
                        ? 0 : request.getCouponRequest().getDiscountAmount())
                .pointsUsed(request.getOnTopRequest() == null
                        ? 0 : request.getOnTopRequest().getPoints()
                )

                .memberTier(request.getOnTopRequest() == null
                        ? MemberTier.NONE : request.getOnTopRequest().getMemberTier()
                )

                .seasonalPercent(request.getSeasonalRequest() == null
                        ? 0 : request.getSeasonalRequest().getSeasonalPercent()
                )
                .everyXBaht(request.getSeasonalRequest() == null ?
                        0 : request.getSeasonalRequest().getEveryXBaht()
                )
                .discountYBaht(request.getSeasonalRequest() == null
                        ? 0 : request.getSeasonalRequest().getDiscountYBaht()
                )
                .build();


        double currentPrice = pricingContext.getCurrentPrice();

        if (request.getCouponRequest() != null) {
            DiscountStrategy coupon = discountStrategyFactory.buildCoupon(request.getCouponRequest());
            currentPrice = coupon.applyDiscount(currentPrice, pricingContext);
            pricingContext.setCurrentPrice(currentPrice);
        }
        if (request.getOnTopRequest() != null) {
            DiscountStrategy onTop = discountStrategyFactory.buildOnTop(request.getOnTopRequest());
            currentPrice = onTop.applyDiscount(currentPrice, pricingContext);
            pricingContext.setCurrentPrice(currentPrice);
        }
        if (request.getSeasonalRequest() != null) {
            DiscountStrategy seasonal = discountStrategyFactory.buildSeasonal(request.getSeasonalRequest());
            currentPrice = seasonal.applyDiscount(currentPrice, pricingContext);
            pricingContext.setCurrentPrice(currentPrice);
        }
        double finalPrice = currentPrice;
        double totalDiscount = basePrice - finalPrice;
        return new CalculatePriceResponse(
                movieRound.getId(),
                basePrice,
                totalDiscount,
                finalPrice
        );


    }


}
