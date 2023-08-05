package ru.practicum.shareit.user.storage;

import ru.practicum.shareit.user.model.User;

import java.util.Collection;

public interface UserStorage {
    User create(User user);

    User get(Integer id);

    User patch(Integer id, User user);

    User delete(Integer id);

    Collection<User> getAll();
}
