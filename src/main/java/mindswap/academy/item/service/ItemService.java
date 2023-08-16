package mindswap.academy.item.service;

import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.dto.ItemDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;

import java.util.List;

public interface ItemService {
    List<Item> getAll();
    Item getByName(String name);
    Item getById(Long id);
    ItemDto create(ItemCreateDto itemCreateDto);
    List<ItemDto> createBulk(List<ItemCreateDto> items);
    Item updateById(Long id, Item item);
    void deleteById(Long id);
    ItemCategory createItemCategory(String name);
    void deleteCategory(String name);

}
