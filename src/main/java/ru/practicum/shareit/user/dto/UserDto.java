package ru.practicum.shareit.user.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}

