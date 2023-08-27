package ru.practicum.shareit.item.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Item;

import java.util.List;


public interface ItemRepository extends JpaRepository <Item, Long> {
    List<Item> findByOwnerEquals(Long owner);
    List<Item> findByNameContainingOrDescriptionContainingIgnoreCase(String text1, String text2);
}
