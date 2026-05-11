package com.ntck.cinematickettrackingsystem.models;

import com.ntck.cinematickettrackingsystem.models.MemberTier.MemberTier;
import com.ntck.cinematickettrackingsystem.models.Status.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "Bookings")
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    private String seatNumber;

    @ManyToOne
    @JoinColumn(
            name = "movie_round_id",
            nullable = false
    )
    private MovieRound movieRound;

    @OneToMany(
            mappedBy = "bookings",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<BookingPromotional> promotions;

    private double originPrice;

    private double finalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private MemberTier memberTier;

    private int pointsUsed;

    private LocalDateTime createdAt;

    private LocalDateTime paidAt;


}
