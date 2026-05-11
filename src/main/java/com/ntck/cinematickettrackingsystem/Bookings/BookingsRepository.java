package com.ntck.cinematickettrackingsystem.Bookings;

import com.ntck.cinematickettrackingsystem.models.Bookings;
import com.ntck.cinematickettrackingsystem.models.MovieRound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingsRepository extends JpaRepository<Bookings, UUID> {

    boolean existsBySeatNumberAndMovieRoundId(String seatNumber, UUID movieRoundId);
}
