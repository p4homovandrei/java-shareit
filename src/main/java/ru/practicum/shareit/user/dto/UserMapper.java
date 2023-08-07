package ru.practicum.shareit.user.dto;

import ru.practicum.shareit.user.model.User;

public class UserMapper {
    public static User fromUserDtoCreate(UserDtoCreate userDtoCreate) {
        return new User(
                userDtoCreate.getName(),
                userDtoCreate.getEmail()
        );
    }

    public static UserDtoCreate toUserDtoCreate(User user) {
        return new UserDtoCreate(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User fromUserDtoPatch(UserDtoPatch userDtoPatch) {
        return new User(
                userDtoPatch.getName(),
                userDtoPatch.getEmail()
        );
    }

    public static UserDtoPatch toUserDtoPatch(User user) {
        return new UserDtoPatch(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User fromUserDtoGet(UserDtoGet userDtoGet) {
        return new User(
                userDtoGet.getId(),
                userDtoGet.getName(),
                userDtoGet.getEmail()
        );
    }

    public static UserDtoGet toUserDtoGet(User user) {
        return new UserDtoGet(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
