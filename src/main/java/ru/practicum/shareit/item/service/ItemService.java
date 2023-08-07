package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDtoCreate;
import ru.practicum.shareit.item.dto.ItemDtoGet;
import ru.practicum.shareit.item.dto.ItemDtoPatch;

import java.util.List;

public interface ItemService {
    public ItemDtoCreate create(ItemDtoCreate item, String owner);

    ItemDtoPatch patch(ItemDtoPatch item, String owner, Integer integer);

    ItemDtoGet get(Integer valueOf);

    List<ItemDtoGet> getAllByOwner(String owner);

    List<ItemDtoGet> search(String text);
}
