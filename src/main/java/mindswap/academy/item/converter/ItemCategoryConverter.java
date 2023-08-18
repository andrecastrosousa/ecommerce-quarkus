package mindswap.academy.item.converter;

import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.item.dto.ItemCategoryCreateDto;
import mindswap.academy.item.dto.ItemCategoryDto;
import mindswap.academy.item.model.ItemCategory;

@ApplicationScoped
public class ItemCategoryConverter {

    public ItemCategory toEntityFromCreateDto(ItemCategoryCreateDto itemCategoryCreateDto){
        return new ItemCategory(itemCategoryCreateDto.getName());
    }
    public ItemCategoryDto toDto(ItemCategory itemCategory){
        return new ItemCategoryDto(itemCategory.getName());
    }
}
