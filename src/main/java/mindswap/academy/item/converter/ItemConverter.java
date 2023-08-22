package mindswap.academy.item.converter;

import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.model.Item;

@ApplicationScoped
public class ItemConverter {

    public Item toEntityFromCreateDto(ItemCreateDto itemCreateDto){
        return Item.builder()
                .withName(itemCreateDto.getName())
                .withPrice(itemCreateDto.getPrice())
                .withCategory(itemCreateDto.getCategories())
                .withDescription(itemCreateDto.getDescription())
                .build();
    }
    public mindswap.academy.item.dto.ItemDto toDto(Item item){
        return new mindswap.academy.item.dto.ItemDto(item.getName(), item.getPrice());
    }



}
