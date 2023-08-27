package ru.practicum.shareit.booking.model;

import lombok.Data;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * TODO Sprint add-bookings.
 */
@Data
@Entity
@Table(name = "bookings", schema = "public")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime start;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime end;

    @Column(name = "item_Id", nullable = false)
    private Long itemId;

    @Column(name = "booker_Id", nullable = false)
    private Long bookerId;

    @Transient
    private Status status;


    public Booking(LocalDateTime start, LocalDateTime end, Long itemId, Long bookerId) {
        this.start = start;
        this.end = end;
        this.itemId = itemId;
        this.bookerId = bookerId;
    }
}
