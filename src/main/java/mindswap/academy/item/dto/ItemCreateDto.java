package mindswap.academy.item.dto;

import mindswap.academy.item.model.ItemCategory;

import java.util.List;

public class ItemCreateDto {
    private String name;

    private Double price;

    private String description;

    private Long itemCategoryId;

    public ItemCreateDto() {
    }

    public ItemCreateDto(String name, Double price, String description, Long itemCategoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.itemCategoryId = itemCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }



    public static ItemCreateDtoBuilder builder(){
        return new ItemCreateDtoBuilder();
    }

    public static final class ItemCreateDtoBuilder{
        private final ItemCreateDto itemCreateDto;

        public ItemCreateDtoBuilder() {
            itemCreateDto = new ItemCreateDto();
        }

        public ItemCreateDtoBuilder withName(String name){
            itemCreateDto.setName(name);
            return this;
        }
        public ItemCreateDtoBuilder withPrice(Double price){
            itemCreateDto.setPrice(price);
            return this;
        }
        public ItemCreateDtoBuilder withDescription(String description){
            itemCreateDto.setDescription(description);
            return this;
        }
        public ItemCreateDtoBuilder withCategory(Long itemCategoryId){
            itemCreateDto.setItemCategoryId(itemCategoryId);
            return this;
        }
        public ItemCreateDto build(){
            return itemCreateDto;
        }

    }

}
