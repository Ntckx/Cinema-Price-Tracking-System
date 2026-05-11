package com.ntck.cinematickettrackingsystem.Bookings;

import com.ntck.cinematickettrackingsystem.models.MovieRound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRoundRepository extends JpaRepository<MovieRound, UUID> {
}
