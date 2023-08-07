package ru.practicum.shareit.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDtoCreate;
import ru.practicum.shareit.item.dto.ItemDtoGet;
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

    UserService userService;
    ItemStorage itemStorage;

    @Autowired
    public ItemServiceImpl(UserService userService, ItemStorage itemStorage) {
        this.userService = userService;
        this.itemStorage = itemStorage;
    }

    @Override
    public ItemDtoCreate create(ItemDtoCreate itemDtoCreate, String owner) {
        checkOwner(owner);
        Item item = ItemMapper.fromItemDtoCreate(itemDtoCreate);
        User user = UserMapper.fromUserDtoGet(userService.get(Integer.valueOf(owner)));
        item.setOwner(user);
        return ItemMapper.toItemDtoCreate(itemStorage.create(item));
    }

    @Override
    public ItemDtoPatch patch(ItemDtoPatch itemDtoPatch, String owner, Integer itemId) {
        checkOwner(owner);
        Item item = ItemMapper.fromItemDtoPatch(itemDtoPatch);
        if (itemStorage.get(itemId).getOwner().getId().equals(Integer.valueOf(owner))) {
            return ItemMapper.toItemDtoPatch(itemStorage.patch(item, itemId));
        } else throw new NoFoundException("Владелец предмета не совпадает с ID запроса на патч");
    }

    @Override
    public ItemDtoGet get(Integer itemId) {
        Item item = itemStorage.get(itemId);
        return ItemMapper.toItemDtoGet(item);
    }

    @Override
    public List<ItemDtoGet> getAllByOwner(String owner) {
        List<Item> list = itemStorage.getAllByOwner(owner);
        List<ItemDtoGet> listDto = new LinkedList<>();
        if (!list.isEmpty()) {
            for (Item item : list) {
                listDto.add(ItemMapper.toItemDtoGet(item));
            }
        }
        return listDto;
    }

    @Override
    public List<ItemDtoGet> search(String text) {
        List<Item> findedList = new LinkedList<>();
        List<ItemDtoGet> dtoList = new LinkedList<>();
        if (text.isBlank()) {
            return dtoList;
        }
        itemStorage.getAll().forEach(item -> findedList.add(searchByName(text, item)));
        findedList.remove(null);
        findedList.forEach(item -> dtoList.add(ItemMapper.toItemDtoGet(item)));
        return dtoList;
    }

    public Item searchByName(String text, Item item) {
        String[] splitName = item.getName().toLowerCase().split(" ");
        for (String s : splitName) {
            if (s.contains(text.toLowerCase()) && item.getAvailable()) {
                return item;
            }
        }
        String[] splitDescrption = item.getDescription().toLowerCase().split(" ");
        for (String s : splitDescrption) {
            if (s.contains(text.toLowerCase()) && item.getAvailable()) {
                return item;
            }
        }
        return null;
    }

    private void checkOwner(String owner) {
        if (owner.isEmpty()) {
            throw new NoArgException("Не указан владелец предмета ");
        }
    }
}
