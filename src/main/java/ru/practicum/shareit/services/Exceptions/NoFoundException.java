package ru.practicum.shareit.services.Exceptions;

public class NoFoundException extends RuntimeException {
    public NoFoundException(String message) {
        super(message);
    }
}
