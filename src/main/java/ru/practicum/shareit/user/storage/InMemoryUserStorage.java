package ru.practicum.shareit.user.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.services.DuplicateException;
import ru.practicum.shareit.services.NoFoundException;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.HashMap;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {

    private final HashMap<Long, User> db = new HashMap<>();
    private Long id = 0L;

    @Override
    public User create(User user) {
        log.info("Create User");
        checkEmail(user, 0L);
        user.setId(getId());
        db.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(Long id) {
        log.info("Get User id " + id);
        if (db.containsKey(id)) {
            return db.get(id);
        } else throw new NoFoundException("Пользователь с id = " + id + "не найден!");
    }

    @Override
    public User patch(Long id, User user) {
        log.info("Patch user");
        checkEmail(user, id);
        if (db.containsKey(id)) {
            if (user.getName() != null) {
                db.get(id).setName(user.getName());
            }
            if (user.getEmail() != null) {
                db.get(id).setEmail(user.getEmail());
            }
            return db.get(id);
        } else throw new NoFoundException("Пользователь с id = " + id + "не найден!");
    }

    @Override
    public User delete(Long id) {
        log.info("delete user");
        if (db.containsKey(id)) {
            return db.remove(id);
        } else throw new NoFoundException("Пользователь с id = " + id + "не найден!");
    }

    @Override
    public Collection<User> getAll() {
        log.info("get all Users");
        return db.values();
    }

    public Long getId() {
        return ++id;
    }

    private void checkEmail(User user, Long id) {
        for (User u : db.values()) {
            if (u.getEmail().equals(user.getEmail())) {
                if (id.equals(u.getId())) {
                    return;
                }
                throw new DuplicateException("Пользователь с таким email уже существует!");
            }
        }
    }
}
