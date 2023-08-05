package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    public Item create(Item item,String owner);

    Item patch(Item item, String owner, Integer integer);

    ItemDto get(Integer valueOf);

    List<ItemDto> getAllByOwner(String owner);

    List<ItemDto> search(String text);
}
