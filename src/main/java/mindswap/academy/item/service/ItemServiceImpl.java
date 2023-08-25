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
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemServiceImpl implements ItemService{
    @Inject
    ItemRepository itemRepository;
    @Inject
    ItemConverter itemConverter;
    @Inject
    ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<ItemDto> getAll() {
        return itemRepository.findAll().list().stream().map(i->itemConverter.toDto(i)).collect(Collectors.toList());
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
//comentario teste
    //outro comentario
    @Override
    public ItemDto create(ItemCreateDto itemCreateDto) {
        if(itemRepository.find("name", itemCreateDto.getName()).firstResultOptional().isPresent()){
            throw new WebApplicationException("Item name already exists",400);
        }
            Long itemCatId =  itemCreateDto.getItemCategoryId();
            ItemCategory cat = itemCategoryRepository.find("id",itemCatId)
                    .firstResultOptional()
                    .orElse(null);
            if(cat==null){
                throw new WebApplicationException("Item Category doesn't exists",400);
            }

        Item item = itemConverter.toEntityFromCreateDto(itemCreateDto, cat);

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
