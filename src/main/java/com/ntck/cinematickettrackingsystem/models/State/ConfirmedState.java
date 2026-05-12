package com.ntck.cinematickettrackingsystem.models.State;

import com.ntck.cinematickettrackingsystem.GlobalException.InvalidStateTransitionException;
import com.ntck.cinematickettrackingsystem.models.State.BookingEvent.BookingEvent;
import com.ntck.cinematickettrackingsystem.models.Status.Status;

public class ConfirmedState implements BookingState {
    @Override
    public Status getStatus(){
        return Status.CONFIRMED;
    }

    @Override
    public BookingState transition(BookingEvent event){
        return switch(event){
            case PAY -> new PaidState();
            case CANCEL -> new CancelledState();
            default -> throw new InvalidStateTransitionException("CONFIRMED cannot make transition to: "+ event);
        };
    }

}
