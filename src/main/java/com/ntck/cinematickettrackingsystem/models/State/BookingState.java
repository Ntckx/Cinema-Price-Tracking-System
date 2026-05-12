package com.ntck.cinematickettrackingsystem.models.State;

import com.ntck.cinematickettrackingsystem.models.State.BookingEvent.BookingEvent;
import com.ntck.cinematickettrackingsystem.models.Status.Status;

public interface BookingState {

    Status getStatus();

    BookingState transition(BookingEvent bookingEvent);

}
