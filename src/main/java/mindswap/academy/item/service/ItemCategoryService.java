package mindswap.academy.item.service;

import mindswap.academy.item.dto.*;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;

import java.util.List;

public interface ItemCategoryService {

    List<ItemCategory> getAll();
    ItemCategory getByName(String name);
    ItemCategory getById(Long id);
    ItemCategoryDto create(ItemCategoryCreateDto itemCategoryCreateDto);
    ItemCategoryDto updateById(Long id, ItemCategoryUpdateDto itemCategoryUpdateDto);
    void deleteById(Long id);


}
