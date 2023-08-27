package ru.practicum.shareit.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoCreate;
import ru.practicum.shareit.item.dto.ItemDtoPatch;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemRepository;
import ru.practicum.shareit.services.Exceptions.NoArgException;
import ru.practicum.shareit.services.Exceptions.NoFoundException;
import ru.practicum.shareit.user.service.UserService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ItemServiceDBImpl implements ItemService {

    private final String eMessage = "Предмет не найден! ";

    ItemRepository repository;

    UserService userService;

    @Autowired
    public ItemServiceDBImpl(ItemRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }


    @Override
    public ItemDto create(ItemDtoCreate item, Long owner) {
        userService.get(owner); //Check user;
        Item newItem = ItemMapper.fromItemDtoCreate(item);
        newItem.setOwner(owner);
        Item createdItem = repository.save(newItem);
        return ItemMapper.toItemDto(createdItem);
    }

    @Override
    public ItemDto patch(ItemDtoPatch item, Long owner, Long id) {
        Item itemPatch = ItemMapper.fromItemDtoPatch(item);
        Item itemFromDB = repository.findById(id).get();
        if (itemFromDB.getOwner().equals(owner)) {
            if (itemPatch.getAvailable() != null)
                itemFromDB.setAvailable(itemPatch.getAvailable());
            if (itemPatch.getName() != null)
                itemFromDB.setName(itemPatch.getName());
            if (itemPatch.getDescription() != null)
                itemFromDB.setDescription(itemPatch.getDescription());
        } else throw new NoFoundException("Владелец предмета не совпадает с ID запроса на патч");
        return ItemMapper.toItemDto(repository.save(itemFromDB));
    }

    @Override
    public ItemDto get(Long id) {
        Optional<Item> item = repository.findById(id);
        if (item.isPresent()) {
            return ItemMapper.toItemDto(item.get());
        } else throw new NoFoundException(eMessage);
    }

    @Override
    public List<ItemDto> getAllByOwner(Long owner) {
        List<Item> list = repository.findByOwnerEquals(owner);
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
        if (text.isEmpty()){
            throw new NoArgException("Аргумент для поиска отсутствует!");
        }
        List<Item> list = new LinkedList<>();
        repository.findByNameContainingOrDescriptionContainingIgnoreCase(text, text).forEach(item -> list.add(item));
        List<ItemDto> listDto = new LinkedList<>();
        list.forEach(item -> {
            if (item.getAvailable().equals(true)) {
                listDto.add(ItemMapper.toItemDto(item));
            }
        });
        return listDto;
    }
}
