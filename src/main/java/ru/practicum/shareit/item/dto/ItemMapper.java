package ru.practicum.shareit.item.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.model.Item;

@UtilityClass
public class ItemMapper {

    public static Item fromItemDtoPatch(ItemDtoPatch itemDtoPatch) {
        return new Item(
                itemDtoPatch.getId(),
                itemDtoPatch.getName(),
                itemDtoPatch.getDescription(),
                itemDtoPatch.getAvailable()
        );
    }

    public static Item fromItemDtoCreate(ItemDtoCreate itemDtoCreate) {
        return new Item(
                itemDtoCreate.getName(),
                itemDtoCreate.getDescription(),
                itemDtoCreate.getAvailable()
        );
    }

    public static ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable()
        );
    }

    public static Item fromItemDto(ItemDto itemDto) {
        return new Item(itemDto.getId(), itemDto.getName(), itemDto.getDescription(),itemDto.getAvailable());
    }
}
