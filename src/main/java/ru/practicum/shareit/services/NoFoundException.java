package ru.practicum.shareit.services;

public class NoFoundException extends RuntimeException {
    public NoFoundException(String message) {
        super(message);
    }
}
