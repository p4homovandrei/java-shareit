package ru.practicum.shareit.booking.service;


import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoCreate;


public interface BookingService {
    public BookingDto create(BookingDtoCreate bookingDtoCreate, Long owner);
}
