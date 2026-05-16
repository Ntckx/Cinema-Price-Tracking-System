package com.ntck.cinematickettrackingsystem.Bookings.Service;

import com.ntck.cinematickettrackingsystem.Bookings.BookingsRepository;
import com.ntck.cinematickettrackingsystem.Bookings.MovieRoundRepository;
import com.ntck.cinematickettrackingsystem.Bookings.dto.request.BookingRequest;
import com.ntck.cinematickettrackingsystem.Bookings.dto.response.BookingResponse;
import com.ntck.cinematickettrackingsystem.GlobalException.BookingNotFoundException;
import com.ntck.cinematickettrackingsystem.GlobalException.MovieRoundNotFoundException;
import com.ntck.cinematickettrackingsystem.GlobalException.SeatAlreadyTakenException;
import com.ntck.cinematickettrackingsystem.models.Bookings;
import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import com.ntck.cinematickettrackingsystem.models.MovieRound;
import com.ntck.cinematickettrackingsystem.State.BookingEvent.BookingEvent;
import com.ntck.cinematickettrackingsystem.State.BookingState;
import com.ntck.cinematickettrackingsystem.State.Factory.BookingStateFactory;
import com.ntck.cinematickettrackingsystem.models.Status.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingsService {

    private final BookingsRepository bookingsRepository;
    private final MovieRoundRepository movieRoundRepository;
    private final BookingMapper bookingMapper;


    @Override
    public BookingResponse bookMovie(BookingRequest bookingRequest) {

        //Find Movie round if not exist throw error
        MovieRound movieRound = movieRoundRepository.findById(bookingRequest.getMovieRoundId())
                .orElseThrow(() -> new MovieRoundNotFoundException(bookingRequest.getMovieRoundId()));


        //Check if seat is already taken
        boolean seatTaken = bookingsRepository.existsBySeatNumberAndMovieRoundIdAndStatusNotIn(bookingRequest.getSeatNumber(),
                bookingRequest.getMovieRoundId(), List.of(Status.CANCELLED, Status.REFUNDED));

        if (seatTaken) {
            throw new SeatAlreadyTakenException("Seat is already taken");
        }

        Bookings booking = Bookings.builder().name(bookingRequest.getName())
                .movieRound(movieRound)
                .seatNumber(bookingRequest.getSeatNumber())
                .basePrice(bookingRequest.getBasePrice())
                .finalPrice(bookingRequest.getBasePrice())
                .status(Status.PENDING)
                .memberTier(MemberTier.NONE)
                .createdAt(LocalDateTime.now()).build();

        return bookingMapper.toResponse(bookingsRepository.save(booking));
    }

    @Override
    public BookingResponse getBookingById(UUID id){
        Bookings bookings = bookingsRepository.findById(id)
                .orElseThrow(()-> new BookingNotFoundException("Booking Not Found"));

        return bookingMapper.toResponse(bookings);
    }

    @Override
    public BookingResponse confirmBooking(UUID id) {
        return transitionBooking(id, BookingEvent.CONFIRM);
    }

    @Override
    public BookingResponse payBooking(UUID id) {
        return transitionBooking(id, BookingEvent.PAY);
    }

    @Override
    public BookingResponse cancelBooking(UUID id) {
        return transitionBooking(id, BookingEvent.CANCEL);
    }

    @Override
    public BookingResponse refundBooking(UUID id) {
        return transitionBooking(id, BookingEvent.REFUND);
    }

    private BookingResponse transitionBooking(UUID id, BookingEvent event){
            Bookings booking = bookingsRepository.findById(id)
                .orElseThrow(()-> new BookingNotFoundException("Booking id is not found with id: " + id));

        BookingState newState = BookingStateFactory.fromCurrentStatus(booking.getStatus())
                .transition(event);

        booking.setStatus(newState.getStatus());

        if(event.equals(BookingEvent.PAY)){
            booking.setPaidAt(LocalDateTime.now());
        }
        return bookingMapper.toResponse(
                bookingsRepository.save(booking)
        );
    }
}
