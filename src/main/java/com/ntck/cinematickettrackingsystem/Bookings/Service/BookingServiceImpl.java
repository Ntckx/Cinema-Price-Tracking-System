package com.ntck.cinematickettrackingsystem.Bookings.Service;

import com.ntck.cinematickettrackingsystem.Bookings.BookingsRepository;
import com.ntck.cinematickettrackingsystem.Bookings.dto.request.BookingRequest;
import com.ntck.cinematickettrackingsystem.Bookings.dto.response.BookingResponse;
import com.ntck.cinematickettrackingsystem.GlobalException.BookingNotFoundException;
import com.ntck.cinematickettrackingsystem.GlobalException.SeatAlreadyTakenException;
import com.ntck.cinematickettrackingsystem.models.Bookings;
import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import com.ntck.cinematickettrackingsystem.models.Status.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingsService {

    private final BookingsRepository bookingsRepository;

    @Override
    public BookingResponse bookMovie(BookingRequest bookingRequest) {
        //Check if booking is already existed
        boolean seatTaken = bookingsRepository.existsBySeatNumberAndMovieName(bookingRequest.getSeatNumber(),
                bookingRequest.getMovieTitle());

        if (seatTaken) {
            throw new SeatAlreadyTakenException("Seat is already taken");
        }

        Bookings booking = Bookings.builder().name(bookingRequest.getName())
                .movieTitle(bookingRequest.getMovieTitle())
                .seatNumber(bookingRequest.getSeatNumber())
                .originPrice(bookingRequest.getOriginPrice())
                .finalPrice(bookingRequest.getOriginPrice())
                .status(Status.PENDING)
                .memberTier(MemberTier.NONE)
                .pointsUsed(0)
                .createdAt(LocalDateTime.now())
                .build();


        Bookings saved = bookingsRepository.save(booking);
        return BookingResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .movieTitle(saved.getMovieTitle())
                .seatNumber(saved.getSeatNumber())
                .originPrice(saved.getOriginPrice())
                .finalPrice(saved.getFinalPrice())
                .status(saved.getStatus())
                .memberTier(saved.getMemberTier())
                .pointsUsed(saved.getPointsUsed())
                .createdAt(saved.getCreatedAt())
                .build();

    }
}
