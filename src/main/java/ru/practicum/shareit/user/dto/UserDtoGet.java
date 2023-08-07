package ru.practicum.shareit.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserDtoGet {
    private Integer id;
    @NotNull
    @Email
    private String email;
    private String name;

    public UserDtoGet(Integer id, String name, String email) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

}

