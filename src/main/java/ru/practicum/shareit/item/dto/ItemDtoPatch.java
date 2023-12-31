package ru.practicum.shareit.item.dto;

import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
public class ItemDtoPatch {

    private Long id;

    private String name;

    private String description;

    private Boolean available;

    public ItemDtoPatch(Long id, String name, String description, Boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.available = available;
    }
}
