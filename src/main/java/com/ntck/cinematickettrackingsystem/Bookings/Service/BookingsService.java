package com.ntck.cinematickettrackingsystem.Bookings.Service;

import com.ntck.cinematickettrackingsystem.Bookings.dto.request.BookingRequest;
import com.ntck.cinematickettrackingsystem.Bookings.dto.response.BookingResponse;

public interface BookingsService {
    //Create Booking
    BookingResponse bookMovie(BookingRequest bookingRequest);
}
