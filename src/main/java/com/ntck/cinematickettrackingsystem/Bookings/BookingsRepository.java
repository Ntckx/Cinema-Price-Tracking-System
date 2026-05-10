package com.ntck.cinematickettrackingsystem.Bookings;

import com.ntck.cinematickettrackingsystem.models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingsRepository extends JpaRepository<Bookings, UUID> {

    boolean existsBySeatNumberAndMovieName(String seatNumber, String movieName);
}
