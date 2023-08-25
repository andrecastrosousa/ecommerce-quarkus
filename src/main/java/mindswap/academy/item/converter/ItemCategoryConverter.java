package mindswap.academy.item.converter;

import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.item.dto.ItemCategoryCreateDto;
import mindswap.academy.item.dto.ItemCategoryDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItemCategoryConverter {

    public ItemCategory toEntityFromCreateDto(ItemCategoryCreateDto itemCategoryCreateDto){
        return new ItemCategory(itemCategoryCreateDto.getName());
    }
    public ItemCategoryDto toDto(ItemCategory itemCategory){
        List<String> items = new ArrayList<>();
        if(itemCategory.getItems() != null){
            for(Item item:itemCategory.getItems()){
                items.add(item.getName());
            }
        }
        return new ItemCategoryDto(itemCategory.getName(), items);
    }
}
