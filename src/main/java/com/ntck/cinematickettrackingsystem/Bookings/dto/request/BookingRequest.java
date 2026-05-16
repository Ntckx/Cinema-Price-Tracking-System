package com.ntck.cinematickettrackingsystem.Bookings.dto.request;

import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import com.ntck.cinematickettrackingsystem.models.MovieRound;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookingRequest {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Seat number cannot be empty")
    private String seatNumber;

    @NotNull(message = "Movie Round must not be null")
    private UUID movieRoundId;

    @Positive(message = "Price must be greater than 0")
    private double basePrice;

    @NotNull(message = "Member tier must not be null")
    private MemberTier memberTier;

    @Min(value = 0, message = "Points used cannot be negative")
    private int pointsUsed;
}
