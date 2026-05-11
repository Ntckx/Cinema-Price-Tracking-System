package com.ntck.cinematickettrackingsystem.Config;

import com.ntck.cinematickettrackingsystem.Bookings.MovieRoundRepository;
import com.ntck.cinematickettrackingsystem.models.MovieRound;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final MovieRoundRepository movieRoundRepository;

    @Override
    public void run(String... args) {
        if (movieRoundRepository.count() == 0) {

            movieRoundRepository.save(MovieRound.builder()
                    .movieTitle("Avengers: Doomsday")
                    .showDate(LocalDate.of(2026, 5, 15))
                    .startTime(LocalTime.of(10, 0))
                    .endTime(LocalTime.of(12, 30))
                    .totalSeats(100)
                    .basePrice(250.0)
                    .build());

            movieRoundRepository.save(MovieRound.builder()
                    .movieTitle("Avengers: Doomsday")
                    .showDate(LocalDate.of(2026, 5, 15))
                    .startTime(LocalTime.of(14, 0))
                    .endTime(LocalTime.of(16, 30))
                    .totalSeats(100)
                    .basePrice(250.0)
                    .build());

            movieRoundRepository.save(MovieRound.builder()
                    .movieTitle("Avengers: Doomsday")
                    .showDate(LocalDate.of(2026, 5, 15))
                    .startTime(LocalTime.of(19, 0))
                    .endTime(LocalTime.of(21, 30))
                    .totalSeats(100)
                    .basePrice(300.0)
                    .build());

            movieRoundRepository.save(MovieRound.builder()
                    .movieTitle("Mission Impossible: The Final Reckoning")
                    .showDate(LocalDate.of(2026, 5, 15))
                    .startTime(LocalTime.of(11, 0))
                    .endTime(LocalTime.of(13, 30))
                    .totalSeats(80)
                    .basePrice(200.0)
                    .build());

            movieRoundRepository.save(MovieRound.builder()
                    .movieTitle("Mission Impossible: The Final Reckoning")
                    .showDate(LocalDate.of(2026, 5, 15))
                    .startTime(LocalTime.of(16, 0))
                    .endTime(LocalTime.of(18, 30))
                    .totalSeats(80)
                    .basePrice(200.0)
                    .build());

            // Michael Jackson — MJ The Musical
            movieRoundRepository.save(MovieRound.builder()
                    .movieTitle("Michael")
                    .showDate(LocalDate.of(2026, 5, 16))
                    .startTime(LocalTime.of(13, 0))
                    .endTime(LocalTime.of(15, 30))
                    .totalSeats(120)
                    .basePrice(350.0)
                    .build());

            movieRoundRepository.save(MovieRound.builder()
                    .movieTitle("Michael")
                    .showDate(LocalDate.of(2026, 5, 16))
                    .startTime(LocalTime.of(18, 0))
                    .endTime(LocalTime.of(20, 30))
                    .totalSeats(120)
                    .basePrice(350.0)
                    .build());

            movieRoundRepository.save(MovieRound.builder()
                    .movieTitle("Michael")
                    .showDate(LocalDate.of(2026, 5, 17))
                    .startTime(LocalTime.of(20, 0))
                    .endTime(LocalTime.of(22, 30))
                    .totalSeats(120)
                    .basePrice(400.0)
                    .build());

            movieRoundRepository.save(MovieRound.builder()
                    .movieTitle("A Minecraft Movie")
                    .showDate(LocalDate.of(2026, 5, 16))
                    .startTime(LocalTime.of(10, 0))
                    .endTime(LocalTime.of(12, 0))
                    .totalSeats(150)
                    .basePrice(180.0)
                    .build());

            movieRoundRepository.save(MovieRound.builder()
                    .movieTitle("A Minecraft Movie")
                    .showDate(LocalDate.of(2026, 5, 16))
                    .startTime(LocalTime.of(14, 0))
                    .endTime(LocalTime.of(16, 0))
                    .totalSeats(150)
                    .basePrice(180.0)
                    .build());

            System.out.println("Movie rounds seeded — 10 rounds across 4 movies");
        }
    }
}
