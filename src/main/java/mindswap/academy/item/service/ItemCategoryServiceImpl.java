package mindswap.academy.item.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.item.converter.ItemCategoryConverter;
import mindswap.academy.item.dto.ItemCategoryCreateDto;
import mindswap.academy.item.dto.ItemCategoryDto;
import mindswap.academy.item.dto.ItemCategoryUpdateDto;
import mindswap.academy.item.model.ItemCategory;
import mindswap.academy.item.repository.ItemCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemCategoryServiceImpl implements ItemCategoryService{

    @Inject
    ItemCategoryRepository itemCategoryRepository;
    @Inject
    ItemCategoryConverter itemCategoryConverter;
    @Override
    public List<ItemCategoryDto> getAll() {
        return itemCategoryRepository.findAll()
                .list()
                .stream()
                .map(ic->itemCategoryConverter.toDto(ic))
                .collect(Collectors.toList());
    }

    @Override
    public ItemCategory getByName(String name) {
        return itemCategoryRepository
                .find("name",name)
                .firstResultOptional()
                .orElseThrow(()->new WebApplicationException("Item not found",404));
    }

    @Override
    public ItemCategory getById(Long id) {
        return itemCategoryRepository
                .findByIdOptional(id)
                .orElseThrow(()->new WebApplicationException("Item not found",404));
    }

    @Override
    public ItemCategoryDto create(ItemCategoryCreateDto itemCategoryCreateDto) {
        if(itemCategoryRepository.find("name", itemCategoryCreateDto.getName()).firstResultOptional().isPresent()) {
            throw new WebApplicationException("Item Category name already exists", 400);
        }
        ItemCategory itemCategory = itemCategoryConverter.toEntityFromCreateDto(itemCategoryCreateDto);
        itemCategoryRepository.persist(itemCategory);
        return itemCategoryConverter.toDto(itemCategory);
    }
    //in progress
    @Override
    public ItemCategoryDto updateById(Long id, ItemCategoryUpdateDto itemCategoryUpdateDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        ItemCategory itemCategory = getById(id);
        itemCategoryRepository.delete(itemCategory);
    }
}
