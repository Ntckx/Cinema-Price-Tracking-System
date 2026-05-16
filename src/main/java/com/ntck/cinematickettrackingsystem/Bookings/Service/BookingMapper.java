package com.ntck.cinematickettrackingsystem.Bookings.Service;

import com.ntck.cinematickettrackingsystem.Bookings.dto.response.BookingResponse;
import com.ntck.cinematickettrackingsystem.models.Bookings;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public BookingResponse toResponse(Bookings booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .name(booking.getName())
                .movieTitle(booking.getMovieRound().getMovieTitle())
                .movieRoundStartTime(booking.getMovieRound().getStartTime())
                .seatNumber(booking.getSeatNumber())
                .basePrice((booking.getBasePrice()))
                .finalPrice(booking.getFinalPrice())
                .status(booking.getStatus())
                .memberTier(booking.getMemberTier())
                .pointsUsed(booking.getPointsUsed())
                .createdAt(booking.getCreatedAt())
                .paidAt(booking.getPaidAt())
                .build();
    }
}
