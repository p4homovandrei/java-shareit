package ru.practicum.shareit.user.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.user.model.User;

@UtilityClass
public class UserMapper {

    public static User fromUserDtoCreate(UserDtoCreate userDtoCreate) {
        return new User(
                userDtoCreate.getName(),
                userDtoCreate.getEmail()
        );
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(
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

    public static User fromUserDto(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail()
        );
    }
}
