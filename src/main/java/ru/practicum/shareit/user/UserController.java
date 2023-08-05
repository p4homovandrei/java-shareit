package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public User create(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @GetMapping("/{userId}")
    public User get(@PathVariable("userId") String id) {
        User user = userService.get(Integer.valueOf(id));
        return user;
    }

    @GetMapping
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @PatchMapping("/{userId}")
    public User patch(@PathVariable("userId") String id, @RequestBody User user) {
        return userService.patch(Integer.valueOf(id), user);
    }

    @DeleteMapping("/{userId}")
    public User delete(@PathVariable("userId") String id) {
        return userService.delete(Integer.valueOf(id));
    }
}
