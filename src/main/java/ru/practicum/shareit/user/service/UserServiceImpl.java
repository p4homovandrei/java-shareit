package ru.practicum.shareit.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserStorage storage;

    public User create(User user) {
        return storage.create(user);
    }

    @Override
    public User get(Integer id) {
        return storage.get(id);
    }

    @Override
    public User patch(Integer id, User user) {
        return storage.patch(id, user);
    }

    @Override
    public User delete(Integer id) {
        return storage.delete(id);
    }

    @Override
    public Collection<User> getAll() {
        return storage.getAll();
    }
}
