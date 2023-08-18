package mindswap.academy.item.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.item.converter.ItemConverter;
import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.dto.ItemDto;
import mindswap.academy.item.dto.ItemUpdateDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;
import mindswap.academy.item.repository.ItemCategoryRepository;
import mindswap.academy.item.repository.ItemRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@ApplicationScoped
public class ItemServiceImpl implements ItemService{
    @Inject
    ItemRepository itemRepository;
    @Inject
    ItemConverter itemConverter;
    @Inject
    ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll().list();
    }

    @Override
    public Item getByName(String name) {
        return itemRepository
                .find("name",name)
                .firstResultOptional()
                .orElseThrow(()->new WebApplicationException("Item not found",404));
    }

    @Override
    public Item getById(Long id) {
        return itemRepository
                .findByIdOptional(id)
                .orElseThrow(()->new WebApplicationException("Item not found",404));
    }

    @Override
    public ItemDto create(ItemCreateDto itemCreateDto) {
        if(itemRepository.find("name", itemCreateDto.getName()).firstResultOptional().isPresent()){
            throw new WebApplicationException("Item name already exists",400);
        }
        //check if the category exits. If doesn't exist, persist.
        for(ItemCategory itemCategory:itemCreateDto.getCategories()){
            ItemCategory cat = itemCategoryRepository.find("name",itemCategory.getName())
                    .firstResultOptional()
                    .orElse(null);
            if(cat==null){
                //itemCategory.getItens().add(itemConverter.toEntityFromCreateDto(itemCreateDto));
                itemCategoryRepository.persist(itemCategory);
            }

        }
        Item item = itemConverter.toEntityFromCreateDto(itemCreateDto);
        itemRepository.persist(item);
        return itemConverter.toDto(item);
    }

    @Override
    public List<ItemDto> createBulk(List<ItemCreateDto> items) {
        return items.stream().map(this::create).toList();
    }

    @Override
    public ItemDto updateById(Long id, ItemUpdateDto itemUpdateDto) {
        Item existingItem = getById(id);
        if(existingItem == null || !existingItem.getId().equals(itemUpdateDto.getId())){
            throw new WebApplicationException("Item name already exists",400);
        }
        existingItem.setName(itemUpdateDto.getName());
        existingItem.setPrice(itemUpdateDto.getPrice());
        existingItem.setDescription(itemUpdateDto.getDescription());
        itemRepository.persist(existingItem);
        return itemConverter.toDto(existingItem);
    }

    @Override
    public void deleteById(Long id) {
        Item item = getById(id);
        itemRepository.delete(item);
    }

}
