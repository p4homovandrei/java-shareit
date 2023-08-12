package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDtoCreate;
import ru.practicum.shareit.user.dto.UserDto;
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
    public UserDto create(@RequestBody @Valid UserDtoCreate user) {
        return userService.create(user);
    }

    @GetMapping("/{userId}")
    public UserDto get(@PathVariable("userId") Long id) {
        return userService.get(id);
    }

    @GetMapping
    public Collection<UserDto> getAll() {
        return userService.getAll();
    }

    @PatchMapping("/{userId}")
    public UserDto patch(@Valid @PathVariable("userId") Long id, @RequestBody UserDtoPatch userDtoPatch) {
        return userService.patch(id, userDtoPatch);
    }

    @DeleteMapping("/{userId}")
    public UserDto delete(@PathVariable("userId") Long id) {
        return userService.delete(id);
    }
}
