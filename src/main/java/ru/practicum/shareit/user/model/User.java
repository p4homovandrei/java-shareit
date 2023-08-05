package ru.practicum.shareit.user.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class User {
    Integer id;
    @NotNull
    @Email
    String email;
    String name;

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

}

