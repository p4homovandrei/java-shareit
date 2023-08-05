package ru.practicum.shareit.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.services.NoArgException;
import ru.practicum.shareit.services.NoFoundException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.LinkedList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    UserService userService;
    ItemStorage itemStorage;


    @Autowired
    public ItemServiceImpl(ItemStorage itemStorage, UserService userService) {
        this.itemStorage = itemStorage;
        this.userService = userService;
    }


    @Override
    public Item create(Item item, String owner) {
        checkOwner(owner);
        User user = userService.get(Integer.valueOf(owner));
        item.setOwner(user);
        return itemStorage.create(item);
    }

    @Override
    public Item patch(Item item, String owner, Integer itemId) {
        checkOwner(owner);
        if (itemStorage.get(itemId).getOwner().getId().equals(Integer.valueOf(owner))) {
            User user = userService.get(Integer.valueOf(owner));
            return itemStorage.patch(item, itemId);
        } else throw new NoFoundException("Владелец предмета не совпадает с ID запроса на патч");
    }

    @Override
    public ItemDto get(Integer itemId) {
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
        List<Item> findedList = new LinkedList();
        List<ItemDto> dtoList = new LinkedList();
        if (text.isBlank()) {
            return dtoList;
        }
        itemStorage.getAll().forEach(item -> findedList.add(searchByName(text, item)));
        findedList.remove(null);
        findedList.forEach(item -> dtoList.add(ItemMapper.toItemDto(item)));
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
