package ru.practicum.shareit.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.user.dto.UserDtoCreate;
import ru.practicum.shareit.user.dto.UserDtoGet;
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

    public UserDtoCreate create(UserDtoCreate userDtoCreate) {
        User user = UserMapper.fromUserDtoCreate(userDtoCreate);
        return UserMapper.toUserDtoCreate(storage.create(user));
    }

    @Override
    public UserDtoGet get(Integer id) {
        return UserMapper.toUserDtoGet(storage.get(id));
    }

    @Override
    public UserDtoPatch patch(Integer id, UserDtoPatch userDtoPatch) {
        User user = UserMapper.fromUserDtoPatch(userDtoPatch);
        return UserMapper.toUserDtoPatch(storage.patch(id, user));
    }

    @Override
    public UserDtoGet delete(Integer id) {
        return UserMapper.toUserDtoGet(storage.delete(id));
    }

    @Override
    public Collection<UserDtoGet> getAll() {
        Collection<UserDtoGet> collection = new LinkedList<>();
        storage.getAll().forEach(user -> collection.add(UserMapper.toUserDtoGet(user)));
        return collection;
    }
}
