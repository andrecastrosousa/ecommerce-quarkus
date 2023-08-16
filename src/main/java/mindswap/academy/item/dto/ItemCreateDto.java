package mindswap.academy.item.dto;

import mindswap.academy.item.model.ItemCategory;

import java.util.List;

public class ItemCreateDto {
    private String name;

    private Double price;

    private String description;

    private List<ItemCategory> categories;

    public ItemCreateDto() {
    }

    public ItemCreateDto(String name, Double price, String description, List<ItemCategory> categories) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.categories = categories;
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

    public List<ItemCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ItemCategory> categories) {
        this.categories = categories;
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
        public ItemCreateDtoBuilder withCategory(List<ItemCategory> categories){
            itemCreateDto.setCategories(categories);
            return this;
        }
        public ItemCreateDto build(){
            return itemCreateDto;
        }

    }

}
