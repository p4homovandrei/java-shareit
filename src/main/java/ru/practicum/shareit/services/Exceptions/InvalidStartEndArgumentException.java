package ru.practicum.shareit.services.Exceptions;

public class InvalidStartEndArgumentException extends RuntimeException{
    public InvalidStartEndArgumentException(String message) {
        super(message);
    }
}
