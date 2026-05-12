package com.ntck.cinematickettrackingsystem.State;

import com.ntck.cinematickettrackingsystem.State.BookingEvent.BookingEvent;
import com.ntck.cinematickettrackingsystem.models.Status.Status;

public interface BookingState {

    Status getStatus();

    BookingState transition(BookingEvent bookingEvent);

}
