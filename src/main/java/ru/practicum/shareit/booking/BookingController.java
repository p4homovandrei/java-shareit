package ru.practicum.shareit.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoCreate;
import ru.practicum.shareit.booking.service.BookingService;

import javax.validation.Valid;

/**
 * TODO Sprint add-bookings.
 */
@RestController
@RequestMapping(path = "/bookings")
public class BookingController {

    BookingService bookingService;
    private static final String OWNER = "X-Sharer-User-Id";
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping
    public BookingDto create(@Valid @RequestBody BookingDtoCreate bookingDtoCreate,
                             @RequestHeader(OWNER) Long owner) {
        return bookingService.create(bookingDtoCreate,owner);
    }
}
