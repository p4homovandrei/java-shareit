package ru.practicum.shareit.booking.dto;

import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;

public  class BookingMapper {

     public static Booking fromBookingDtoCreate(BookingDtoCreate dto){
         return new Booking(
                 dto.getStart(),
                 dto.getEnd(),
                 dto.getItemId(),
                 dto.getBookerId()
         );
    }

    public static BookingDto toBookingDto(Booking booking){
         return new BookingDto(
                 booking.getId(),
                 booking.getStart(),
                 booking.getEnd()
         );
    }
}
