package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage {
    Item create(Item item);

    Item patch(Item item, Long itemId);

    Item get(Long itemId);

    List<Item> getAllByOwner(Long owner);

    List<Item> getAll();
}
