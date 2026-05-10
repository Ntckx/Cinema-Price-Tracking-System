package com.ntck.cinematickettrackingsystem.models;

import com.ntck.cinematickettrackingsystem.models.Promotion.Category.PromotionCategory;
import com.ntck.cinematickettrackingsystem.models.Promotion.Type.Promotion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "BookingPromotional")
@Table(name = "bookings_Promotional")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingPromotional {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(
            name = "bookings_id", nullable = false
    )
    private Bookings bookings;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Promotion promotion;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PromotionCategory promotionCategory;

    @Positive
    private double discountedAmount;
}
