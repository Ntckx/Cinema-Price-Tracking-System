package com.ntck.cinematickettrackingsystem.Bookings.Service;

import com.ntck.cinematickettrackingsystem.Bookings.dto.request.BookingRequest;
import com.ntck.cinematickettrackingsystem.Bookings.dto.response.BookingResponse;
import com.ntck.cinematickettrackingsystem.models.State.BookingEvent.BookingEvent;

import java.util.UUID;

public interface BookingsService {
    //Create Booking
    BookingResponse bookMovie(BookingRequest bookingRequest);
    BookingResponse transitionBooking(UUID id, BookingEvent event);
}
