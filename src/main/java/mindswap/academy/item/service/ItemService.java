package mindswap.academy.item.service;

import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.dto.ItemDto;
import mindswap.academy.item.dto.ItemUpdateDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;

import java.util.List;

public interface ItemService {
    List<ItemDto> getAll();
    Item getByName(String name);
    Item getById(Long id);
    ItemDto create(ItemCreateDto itemCreateDto);
    List<ItemDto> createBulk(List<ItemCreateDto> items);
    ItemDto updateById(Long id, ItemUpdateDto itemUpdateDto);
    void deleteById(Long id);

}
