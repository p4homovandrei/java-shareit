package ru.practicum.shareit.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserDtoCreate {

    private Integer id;

    @NotNull
    @Email
    private String email;

    private String name;

    public UserDtoCreate(String email, String name) {
        this.email = email;
        this.name = name;
    }

}
