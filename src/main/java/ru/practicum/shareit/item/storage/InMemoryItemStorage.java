package ru.practicum.shareit.item.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.services.NoFoundException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class InMemoryItemStorage implements ItemStorage {

    private final HashMap<Integer, Item> db = new HashMap<>();
    private Integer id = 0;

    @Override
    public Item create(Item item) {
        log.info("Create Item");
        item.setId(getId());
        db.put(item.getId(), item);
        return item;
    }

    @Override
    public Item patch(Item item, Integer itemId) {
        log.info("Patch item");
        if (db.containsKey(itemId)) {
            Item patchItem = db.get(itemId);
            if (item.getAvailable() != null)
                patchItem.setAvailable(item.getAvailable());
            if (item.getName() != null)
                patchItem.setName(item.getName());
            if (item.getOwner() != null)
                patchItem.setOwner(item.getOwner());
            if (item.getRequest() != null)
                patchItem.setRequest(item.getRequest());
            if (item.getDescription() != null)
                patchItem.setDescription(item.getDescription());
            db.put(itemId, patchItem);
            return patchItem;
        } else throw new NoFoundException("предмет с ID = " + item.getId() + " не найден!");
    }

    @Override
    public Item get(Integer itemId) {
        if (db.containsKey(itemId)) {
            return db.get(itemId);
        } else throw new NoFoundException("предмет с ID = " + itemId + " не найден!");
    }

    @Override
    public List<Item> getAllByOwner(String owner) {
        List<Item> list = new LinkedList<Item>();
        for (Item item : db.values()) {
            if (item.getOwner().getId().equals(Integer.valueOf(owner))) {
                list.add(item);
            }
        }
        return list;
    }

    @Override
    public List<Item> getAll() {
        return List.copyOf(db.values());
    }

    public Integer getId() {
        return ++id;
    }


}
