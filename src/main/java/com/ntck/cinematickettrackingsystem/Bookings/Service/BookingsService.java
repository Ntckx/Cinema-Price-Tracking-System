package com.ntck.cinematickettrackingsystem.Bookings.Service;

import com.ntck.cinematickettrackingsystem.Bookings.dto.request.BookingRequest;
import com.ntck.cinematickettrackingsystem.Bookings.dto.response.BookingResponse;
import com.ntck.cinematickettrackingsystem.State.BookingEvent.BookingEvent;
import com.ntck.cinematickettrackingsystem.models.Bookings;

import java.util.UUID;

public interface BookingsService {
    //Create Booking
    BookingResponse bookMovie(BookingRequest bookingRequest);
    BookingResponse getBookingById(UUID id);
    BookingResponse confirmBooking(UUID id);
    BookingResponse payBooking(UUID id);
    BookingResponse cancelBooking(UUID id);
    BookingResponse refundBooking(UUID id);

}
