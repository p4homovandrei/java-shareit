package ru.practicum.shareit.services.Exceptions;

public class NoAvailableException extends RuntimeException {
    public NoAvailableException(String s) {
        super(s);
    }
}
