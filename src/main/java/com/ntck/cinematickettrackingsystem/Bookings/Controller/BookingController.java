package com.ntck.cinematickettrackingsystem.Bookings.Controller;

import com.ntck.cinematickettrackingsystem.Bookings.Service.BookingsService;
import com.ntck.cinematickettrackingsystem.Bookings.dto.request.BookingRequest;
import com.ntck.cinematickettrackingsystem.Bookings.dto.response.BookingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingsService bookingsService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createBooking(@Valid @RequestBody BookingRequest request){
       return bookingsService.bookMovie(request);
    }

//    ConfirmBooking
    @PatchMapping("/{id}/confirm-booking")
    @ResponseStatus(HttpStatus.OK)
    public BookingResponse confirmBooking(@PathVariable UUID id){
        return bookingsService.confirmBooking(id);
    }

//     GetBookingById
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookingResponse getBooking(@PathVariable UUID id){
        return bookingsService.getBookingById(id);
    }

//    PayBooking
    @PatchMapping("/{id}/pay-booking")
    @ResponseStatus(HttpStatus.OK)
    public BookingResponse payBooking(@PathVariable UUID id){
        return bookingsService.payBooking(id);
    }

//    CancelBooking
    @PatchMapping("/{id}/cancel-booking")
    @ResponseStatus(HttpStatus.OK)
    public BookingResponse cancelBooking(@PathVariable UUID id){
        return bookingsService.cancelBooking(id);
    }

//    RefundBooking
    @PatchMapping("/{id}/refund-booking")
    @ResponseStatus(HttpStatus.OK)
    public BookingResponse refundBooking(@PathVariable UUID id){
        return bookingsService.refundBooking(id);
    }




}
