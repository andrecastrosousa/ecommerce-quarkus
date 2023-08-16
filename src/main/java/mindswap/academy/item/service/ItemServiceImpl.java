package mindswap.academy.item.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.item.converter.ItemConverter;
import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.dto.ItemDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;
import mindswap.academy.item.repository.ItemCategoryRepository;
import mindswap.academy.item.repository.ItemRepository;

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
        Item item = itemConverter.toEntityFromCreateDto(itemCreateDto);
        itemRepository.persist(item);
        return itemConverter.toDto(item);
    }

    @Override
    public List<ItemDto> createBulk(List<ItemCreateDto> items) {
        return items.stream().map(this::create).toList();
    }

    @Override
    public Item updateById(Long id, Item item) {
        Item existingItem = getById(id);
        existingItem.setName(item.getName());
        existingItem.setPrice(item.getPrice());
        existingItem.setDescription(item.getDescription());
        itemRepository.persist(existingItem);
        return existingItem;
    }

    @Override
    public void deleteById(Long id) {
        Item item = getById(id);
        itemRepository.delete(item);
    }
    @Override
    public ItemCategory createItemCategory(String name){
        ItemCategory itemCategory = new ItemCategory(name);
        itemCategoryRepository.persist(itemCategory);
        return itemCategory;
    }

    @Override
    public void deleteCategory(String name) {
        ItemCategory existingItemCategory = itemCategoryRepository
                .find("name", name)
                .firstResultOptional()
                .orElseThrow(()->new WebApplicationException("Item category doesn't exist",400));
        itemCategoryRepository.delete(existingItemCategory);
    }
}
