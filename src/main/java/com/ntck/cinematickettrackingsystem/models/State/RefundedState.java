package com.ntck.cinematickettrackingsystem.models.State;

import com.ntck.cinematickettrackingsystem.GlobalException.InvalidStateTransitionException;
import com.ntck.cinematickettrackingsystem.models.State.BookingEvent.BookingEvent;
import com.ntck.cinematickettrackingsystem.models.Status.Status;

public class RefundedState implements BookingState{
    @Override
    public Status getStatus(){
        return Status.REFUNDED;
    }

    @Override
    public BookingState transition(BookingEvent event){
        throw new InvalidStateTransitionException("Refund cannot make transition to " + event);
    }
}
