package ru.practicum.shareit.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoCreate;
import ru.practicum.shareit.booking.dto.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.storage.BookingRepository;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.services.Exceptions.InvalidStartEndArgumentException;
import ru.practicum.shareit.services.Exceptions.NoAvailableException;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

@Service
public class BookingServiceDBImpl implements BookingService {


    BookingRepository bookingRepository;
    UserService userService;

    ItemService itemService;

    @Autowired
    public BookingServiceDBImpl(BookingRepository bookingRepository, UserService userService,
                                ItemService itemService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.itemService = itemService;
    }

    @Override
    public BookingDto create(BookingDtoCreate bookingDtoCreate, Long owner) throws InvalidStartEndArgumentException {
        User user = UserMapper.fromUserDto(userService.get(owner));
        checkStartEndBooking(bookingDtoCreate);// check user
        if (itemService.get(bookingDtoCreate.getItemId()).getAvailable()) {
            bookingDtoCreate.setBookerId(owner);
            Booking s = bookingRepository.save(BookingMapper.fromBookingDtoCreate(bookingDtoCreate));
            BookingDto bookingDto = BookingMapper.toBookingDto(s);
            bookingDto.setBooker(user);
            Item item = ItemMapper.fromItemDto(itemService.get(bookingDtoCreate.getItemId()));
            bookingDto.setItem(item);
            return bookingDto;
        } else throw new NoAvailableException("Предмет не доступен!");
    }

    private void checkStartEndBooking(BookingDtoCreate bookingDtoCreate) {
        if (bookingDtoCreate.getStart().equals(bookingDtoCreate.getEnd())) {
            throw new InvalidStartEndArgumentException("Аргументы Start и End равны!");
        }
        if (bookingDtoCreate.getStart().isAfter(bookingDtoCreate.getEnd())) {
            throw new InvalidStartEndArgumentException("Аргумент Start позже End");
        }
    }
}
