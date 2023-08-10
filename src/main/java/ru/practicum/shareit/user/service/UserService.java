package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDtoCreate;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserDtoPatch;

import java.util.Collection;

public interface UserService {
    public UserDto create(UserDtoCreate user);

    UserDto get(Long id);

    UserDto patch(Long id, UserDtoPatch user);

    UserDto delete(Long id);

    Collection<UserDto> getAll();
}
