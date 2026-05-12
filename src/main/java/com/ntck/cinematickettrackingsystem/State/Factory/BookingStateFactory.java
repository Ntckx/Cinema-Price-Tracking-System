package com.ntck.cinematickettrackingsystem.State.Factory;

import com.ntck.cinematickettrackingsystem.State.*;
import com.ntck.cinematickettrackingsystem.models.Status.Status;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookingStateFactory {

    public static BookingState fromCurrentStatus(Status status) {
        return switch (status) {
            case PENDING -> new PendingState();
            case CONFIRMED -> new ConfirmedState();
            case PAID -> new PaidState();
            case REFUNDED -> new RefundedState();
            case CANCELLED -> new CancelledState();
        };
    }
}
