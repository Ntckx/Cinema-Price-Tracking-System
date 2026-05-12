package com.ntck.cinematickettrackingsystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "movie_rounds")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieRound {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String movieTitle;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int totalSeats;
    private double basePrice;
}
