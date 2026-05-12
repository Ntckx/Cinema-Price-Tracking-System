package com.ntck.cinematickettrackingsystem.State;

import com.ntck.cinematickettrackingsystem.GlobalException.InvalidStateTransitionException;
import com.ntck.cinematickettrackingsystem.State.BookingEvent.BookingEvent;
import com.ntck.cinematickettrackingsystem.models.Status.Status;

public class PendingState implements BookingState {

    @Override
    public Status getStatus(){
        return Status.PENDING;
    }

    @Override
    public BookingState transition(BookingEvent event){
        return switch(event){
            case CONFIRM -> new ConfirmedState();
            case CANCEL -> new CancelledState();
            default -> throw new InvalidStateTransitionException("PENDING cannot make transition with event:" + event);

        };
    }
}
