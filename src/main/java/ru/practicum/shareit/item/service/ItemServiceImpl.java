package ru.practicum.shareit.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoCreate;
import ru.practicum.shareit.item.dto.ItemDtoPatch;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.services.NoArgException;
import ru.practicum.shareit.services.NoFoundException;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.LinkedList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private UserService userService;
    private ItemStorage itemStorage;

    @Autowired
    public ItemServiceImpl(UserService userService, ItemStorage itemStorage) {
        this.userService = userService;
        this.itemStorage = itemStorage;
    }

    @Override
    public ItemDto create(ItemDtoCreate itemDtoCreate, String owner) {
        checkOwner(owner);
        Item item = ItemMapper.fromItemDtoCreate(itemDtoCreate);
        User user = UserMapper.fromUserDto(userService.get(Long.valueOf(owner)));
        item.setOwner(user);
        return ItemMapper.toItemDto(itemStorage.create(item));
    }

    @Override
    public ItemDto patch(ItemDtoPatch itemDtoPatch, String owner, Long itemId) {
        checkOwner(owner);
        Item item = ItemMapper.fromItemDtoPatch(itemDtoPatch);
        if (itemStorage.get(itemId).getOwner().getId().equals(Long.valueOf(owner))) {
            return ItemMapper.toItemDto(itemStorage.patch(item, itemId));
        } else throw new NoFoundException("Владелец предмета не совпадает с ID запроса на патч");
    }

    @Override
    public ItemDto get(Long itemId) {
        Item item = itemStorage.get(itemId);
        return ItemMapper.toItemDto(item);
    }

    @Override
    public List<ItemDto> getAllByOwner(String owner) {
        List<Item> list = itemStorage.getAllByOwner(owner);
        List<ItemDto> listDto = new LinkedList<>();
        if (!list.isEmpty()) {
            for (Item item : list) {
                listDto.add(ItemMapper.toItemDto(item));
            }
        }
        return listDto;
    }

    @Override
    public List<ItemDto> search(String text) {
        List<Item> findedList = new LinkedList<>();
        List<ItemDto> dtoList = new LinkedList<>();
        if (text.isBlank()) {
            return dtoList;
        }
        itemStorage.getAll().forEach(item -> findedList.add(searchByName(text, item)));
        findedList.remove(null);// добавляются нули (изменяется размер) из searchByName поэтому надо удалить;
        findedList.forEach(item -> dtoList.add(ItemMapper.toItemDto(item)));
        return dtoList;
    }

    public Item searchByName(String text, Item item) {
            if ((item.getName().toLowerCase().contains(text.toLowerCase())
                || item.getDescription().toLowerCase().contains(text.toLowerCase()))
                    && item.getAvailable()) {
                return item;
            }
        return null;
    }

    private void checkOwner(String owner) {
        if (owner.isEmpty()) {
            throw new NoArgException("Не указан владелец предмета ");
        }
    }
}
