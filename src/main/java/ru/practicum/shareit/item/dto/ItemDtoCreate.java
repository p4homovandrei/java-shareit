package ru.practicum.shareit.item.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data

public class ItemDtoCreate {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Boolean available;

    public ItemDtoCreate(String name, String description, Boolean available) {
        this.name = name;
        this.description = description;
        this.available = available;
    }
}
