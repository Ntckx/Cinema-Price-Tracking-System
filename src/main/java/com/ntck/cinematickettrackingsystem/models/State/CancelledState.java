package com.ntck.cinematickettrackingsystem.models.State;

import com.ntck.cinematickettrackingsystem.GlobalException.InvalidStateTransitionException;
import com.ntck.cinematickettrackingsystem.models.State.BookingEvent.BookingEvent;
import com.ntck.cinematickettrackingsystem.models.Status.Status;

public class CancelledState implements BookingState {

    @Override
    public Status getStatus() {
        return Status.CANCELLED;
    }

    @Override
    public BookingState transition(BookingEvent event) {
        throw new InvalidStateTransitionException("This is terminal state. Your booking is cancelled");
    }
}
