package com.ntck.cinematickettrackingsystem.models;

import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import com.ntck.cinematickettrackingsystem.models.Status.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "Bookings")
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Seat number")
    private String seatNumber;

    @NotEmpty(message = "Movie Title cannot be empty" )
    private String movieTitle;

    @OneToMany(
            mappedBy = "bookings",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<BookingPromotional> promotions;

    @Positive(message = "Price must be more than 0")
    private double originPrice;

    @Positive(message = "Final price must be more than 0")
    private double finalPrice;

    @NotNull(message = "Status Must not be empty")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "User must have tier")
    @Enumerated(EnumType.STRING)
    private MemberTier memberTier;

    @Positive(message = "Points used must be more than 0")
    private int pointsUsed;

    private LocalDateTime createdAt;

    private LocalDateTime paidAt;







}
