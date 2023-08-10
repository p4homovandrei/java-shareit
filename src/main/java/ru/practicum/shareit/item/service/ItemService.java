package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDtoCreate;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoPatch;

import java.util.List;

public interface ItemService {

    ItemDto create(ItemDtoCreate item, String owner);

    ItemDto patch(ItemDtoPatch item, String owner, Long integer);

    ItemDto get(Long valueOf);

    List<ItemDto> getAllByOwner(String owner);

    List<ItemDto> search(String text);
}
