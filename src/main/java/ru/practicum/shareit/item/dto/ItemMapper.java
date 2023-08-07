package ru.practicum.shareit.item.dto;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;

@Component
public class ItemMapper {

    public static Item fromItemDtoCreate(ItemDtoCreate itemDtoCreate) {
        return new Item(
                itemDtoCreate.getName(),
                itemDtoCreate.getDescription(),
                itemDtoCreate.getAvailable()
        );
    }

    public static ItemDtoCreate toItemDtoCreate(Item item) {
        return new ItemDtoCreate(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable()
        );
    }

    public static Item fromItemDtoPatch(ItemDtoPatch itemDtoPatch) {
        return new Item(
                itemDtoPatch.getId(),
                itemDtoPatch.getName(),
                itemDtoPatch.getDescription(),
                itemDtoPatch.getAvailable()
        );
    }

    public static ItemDtoPatch toItemDtoPatch(Item item) {
        return new ItemDtoPatch(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable()
        );
    }

    public static ItemDtoGet toItemDtoGet(Item item) {
        return new ItemDtoGet(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable()
        );
    }
}
