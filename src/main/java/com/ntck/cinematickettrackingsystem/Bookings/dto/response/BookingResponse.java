package com.ntck.cinematickettrackingsystem.Bookings.dto.response;

import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import com.ntck.cinematickettrackingsystem.models.Status.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookingResponse {
    private UUID id;
    private String name;
    private String movieTitle;
    private String seatNumber;
    private double originPrice;
    private double finalPrice;
    private Status status;
    private MemberTier memberTier;
    private int pointsUsed;
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;
}
