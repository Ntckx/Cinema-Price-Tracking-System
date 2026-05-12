package com.ntck.cinematickettrackingsystem.Bookings;

import com.ntck.cinematickettrackingsystem.models.Bookings;
import com.ntck.cinematickettrackingsystem.models.MovieRound;
import com.ntck.cinematickettrackingsystem.models.Status.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingsRepository extends JpaRepository<Bookings, UUID> {

    boolean existsBySeatNumberAndMovieRoundIdAndStatusNotIn(String seatNumber, UUID movieRoundId, List<Status> status);

}
