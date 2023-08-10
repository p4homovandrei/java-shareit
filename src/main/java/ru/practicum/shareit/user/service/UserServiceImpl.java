package ru.practicum.shareit.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDtoCreate;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserDtoPatch;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.Collection;
import java.util.LinkedList;

@Service
public class UserServiceImpl implements UserService {

    UserStorage storage;

    @Autowired
    public UserServiceImpl(UserStorage storage) {
        this.storage = storage;
    }

    public UserDto create(UserDtoCreate userDtoCreate) {
        User user = UserMapper.fromUserDtoCreate(userDtoCreate);
        return UserMapper.toUserDto(storage.create(user));
    }

    @Override
    public UserDto get(Long id) {
        return UserMapper.toUserDto(storage.get(id));
    }

    @Override
    public UserDto patch(Long id, UserDtoPatch userDtoPatch) {
        User user = UserMapper.fromUserDtoPatch(userDtoPatch);
        return UserMapper.toUserDto(storage.patch(id, user));
    }

    @Override
    public UserDto delete(Long id) {
        return UserMapper.toUserDto(storage.delete(id));
    }

    @Override
    public Collection<UserDto> getAll() {
        Collection<UserDto> collection = new LinkedList<>();
        storage.getAll().forEach(user -> collection.add(UserMapper.toUserDto(user)));
        return collection;
    }
}
