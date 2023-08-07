package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDtoCreate;
import ru.practicum.shareit.user.dto.UserDtoGet;
import ru.practicum.shareit.user.dto.UserDtoPatch;

import java.util.Collection;

public interface UserService {
    public UserDtoCreate create(UserDtoCreate user);

    UserDtoGet get(Integer id);

    UserDtoPatch patch(Integer id, UserDtoPatch user);

    UserDtoGet delete(Integer id);

    Collection<UserDtoGet> getAll();
}
