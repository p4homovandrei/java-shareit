package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDtoCreate;
import ru.practicum.shareit.item.dto.ItemDtoGet;
import ru.practicum.shareit.item.dto.ItemDtoPatch;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    public static final String OWNER = "X-Sharer-User-Id";


    @PostMapping
    public ItemDtoCreate create(@Valid @RequestBody ItemDtoCreate itemDtoCreate,
                                @RequestHeader(OWNER) String owner) {
        return itemService.create(itemDtoCreate, owner);
    }

    @PatchMapping("/{itemId}")
    public ItemDtoPatch patch(@RequestBody ItemDtoPatch itemDtoPatch, @RequestHeader(OWNER) String owner,
                              @PathVariable("itemId") String itemId) {
        return itemService.patch(itemDtoPatch, owner, Integer.valueOf(itemId));
    }

    @GetMapping("/{itemId}")
    public ItemDtoGet get(@PathVariable("itemId") String itemId) {
        return itemService.get(Integer.valueOf(itemId));
    }

    @GetMapping
    public List<ItemDtoGet> getAll(@RequestHeader(OWNER) String owner) {
        return itemService.getAllByOwner(owner);
    }

    @GetMapping("/search")
    public List<ItemDtoGet> search(@RequestParam(name = "text") String text) {
        return itemService.search(text);
    }
}
