package com.ntck.cinematickettrackingsystem.models.State;

import com.ntck.cinematickettrackingsystem.GlobalException.InvalidStateTransitionException;
import com.ntck.cinematickettrackingsystem.models.State.BookingEvent.BookingEvent;
import com.ntck.cinematickettrackingsystem.models.Status.Status;

public class PaidState implements BookingState{
    @Override
    public Status getStatus(){
        return Status.PAID;
    }

    @Override
    public BookingState transition(BookingEvent event){
        return switch (event){
            case REFUND -> new RefundedState();
            default -> throw new InvalidStateTransitionException("PAID cannot make transition to" + event);
        };
    }
}
