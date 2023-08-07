package ru.practicum.shareit.item.dto;

import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class ItemDtoPatch {


    private Integer id;

    private String name;

    private String description;

    private Boolean available;

    private String request;

    public ItemDtoPatch(Integer id, String name, String description, Boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
    }
}
