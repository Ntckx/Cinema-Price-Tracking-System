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
import com.ntck.cinematickettrackingsystem.models.State.BookingEvent.BookingEvent;
import com.ntck.cinematickettrackingsystem.models.State.BookingState;
import com.ntck.cinematickettrackingsystem.models.State.Factory.BookingStateFactory;
import com.ntck.cinematickettrackingsystem.models.Status.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        boolean seatTaken = bookingsRepository.existsBySeatNumberAndMovieRoundId(bookingRequest.getSeatNumber(),
                bookingRequest.getMovieRoundId());

        if (seatTaken) {
            throw new SeatAlreadyTakenException("Seat is already taken");
        }

        Bookings booking = Bookings.builder().name(bookingRequest.getName())
                .movieRound(movieRound)
                .seatNumber(bookingRequest.getSeatNumber())
                .originPrice(bookingRequest.getOriginPrice())
                .finalPrice(bookingRequest.getOriginPrice())
                .status(Status.PENDING)
                .memberTier(MemberTier.NONE)
                .pointsUsed(0)
                .createdAt(LocalDateTime.now()).build();


        Bookings saved = bookingsRepository.save(booking);
        return BookingResponse.builder().id(saved.getId())
                .name(saved.getName())
                .movieTitle(saved.getMovieRound().getMovieTitle())
                .movieRoundStartTime(saved.getMovieRound().getStartTime())
                .seatNumber(saved.getSeatNumber())
                .originPrice(saved.getOriginPrice())
                .finalPrice(saved.getFinalPrice())
                .status(saved.getStatus())
                .memberTier(saved.getMemberTier())
                .pointsUsed(saved.getPointsUsed())
                .createdAt(saved.getCreatedAt()).build();

    }

    @Override
    public BookingResponse transitionBooking(UUID id, BookingEvent event){
        Bookings booking = bookingsRepository.findById(id)
                .orElseThrow(()-> new BookingNotFoundException(String.valueOf(id)));

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
