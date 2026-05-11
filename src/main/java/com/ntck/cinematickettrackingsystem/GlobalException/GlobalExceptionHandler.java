package com.ntck.cinematickettrackingsystem.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    public ProblemDetail handleBookingNotFound(BookingNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Booking Not Found");
        problemDetail.setProperty("timestamp:", LocalDateTime.now());

        return problemDetail;
    }

    @ExceptionHandler(MovieRoundNotFoundException.class)
    public ProblemDetail handleMovieRoundNotFound(MovieRoundNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Movie Round is not found");
        problemDetail.setProperty("timestamp: ", LocalDateTime.now());

        return problemDetail;
    }
}
