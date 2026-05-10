package com.ntck.cinematickettrackingsystem.Bookings.dto.request;

import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import jakarta.validation.constraints.*;
import lombok.*;

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

    @NotBlank(message = "Movie title cannot be empty")
    private String movieTitle;

    @Positive(message = "Price must be greater than 0")
    private double originPrice;

    @NotNull(message = "Member tier must not be null")
    private MemberTier memberTier;

    @Min(value = 0, message = "Points used cannot be negative")
    private int pointsUsed;
}
