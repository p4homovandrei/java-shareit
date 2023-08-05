package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping
    public Item create(@Valid @RequestBody Item item, @RequestHeader("X-Sharer-User-Id") String owner) {
        return itemService.create(item, owner);
    }

    @PatchMapping("/{itemId}")
    public Item patch(@RequestBody Item item, @RequestHeader("X-Sharer-User-Id") String owner, @PathVariable("itemId") String itemId) {
        return itemService.patch(item, owner, Integer.valueOf(itemId));
    }

    @GetMapping("/{itemId}")
    public ItemDto get(@PathVariable("itemId") String itemId) {
        return itemService.get(Integer.valueOf(itemId));
    }

    @GetMapping
    public List<ItemDto> getAll(@RequestHeader("X-Sharer-User-Id") String owner) {
        return itemService.getAllByOwner(owner);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam(name = "text") String text) {
        return itemService.search(text);
    }
}
