package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage {
    Item create(Item item);

    Item patch(Item item, Integer itemId);

    Item get(Integer itemId);

    List<Item> getAllByOwner(String owner);

    List<Item> getAll();
}
