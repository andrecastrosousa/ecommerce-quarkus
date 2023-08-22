package mindswap.academy.item.service;

import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.dto.ItemUpdateDto;
import mindswap.academy.item.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAll();
    Item getByName(String name);
    Item getById(Long id);
    mindswap.academy.item.dto.ItemDto create(ItemCreateDto itemCreateDto);
    List<mindswap.academy.item.dto.ItemDto> createBulk(List<ItemCreateDto> items);
    mindswap.academy.item.dto.ItemDto updateById(Long id, ItemUpdateDto itemUpdateDto);
    void deleteById(Long id);

}
