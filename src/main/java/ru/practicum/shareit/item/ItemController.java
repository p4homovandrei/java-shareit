package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDtoCreate;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoPatch;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    private static final String OWNER = "X-Sharer-User-Id";

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ItemDto create(@Valid @RequestBody ItemDtoCreate itemDtoCreate,
                                @RequestHeader(OWNER) String owner) {
        return itemService.create(itemDtoCreate, owner);
    }

    @PatchMapping("/{itemId}")
    public ItemDto patch(@RequestBody ItemDtoPatch itemDtoPatch, @RequestHeader(OWNER) String owner,
                              @PathVariable("itemId") Long itemId) {
        return itemService.patch(itemDtoPatch, owner, itemId);
    }

    @GetMapping("/{itemId}")
    public ItemDto get(@PathVariable("itemId") Long itemId) {
        return itemService.get(itemId);
    }

    @GetMapping
    public List<ItemDto> getAll(@RequestHeader(OWNER) String owner) {
        return itemService.getAllByOwner(owner);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam(name = "text") String text) {
        return itemService.search(text);
    }
}
