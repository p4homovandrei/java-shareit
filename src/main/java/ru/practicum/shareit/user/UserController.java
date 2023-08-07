package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDtoCreate;
import ru.practicum.shareit.user.dto.UserDtoGet;
import ru.practicum.shareit.user.dto.UserDtoPatch;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDtoCreate create(@RequestBody @Valid UserDtoCreate user) {
        return userService.create(user);
    }

    @GetMapping("/{userId}")
    public UserDtoGet get(@PathVariable("userId") String id) {
        return userService.get(Integer.valueOf(id));
    }

    @GetMapping
    public Collection<UserDtoGet> getAll() {
        return userService.getAll();
    }

    @PatchMapping("/{userId}")
    public UserDtoPatch patch(@PathVariable("userId") String id, @RequestBody UserDtoPatch userDtoPatch) {
        return userService.patch(Integer.valueOf(id), userDtoPatch);
    }

    @DeleteMapping("/{userId}")
    public UserDtoGet delete(@PathVariable("userId") String id) {
        return userService.delete(Integer.valueOf(id));
    }
}
