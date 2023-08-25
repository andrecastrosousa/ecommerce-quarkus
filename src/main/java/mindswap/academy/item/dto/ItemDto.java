package mindswap.academy.item.dto;

import mindswap.academy.item.model.Review;

import java.util.List;

public class ItemDto {
    //adicionar o Id em todos os Dtos
    private String name;

    private Double price;

    private List<ReviewDto> reviewsDto;

    private String itemCategory;

    public ItemDto() {
    }

    public ItemDto(String name, Double price, String itemCategory, List<ReviewDto>reviewsDto) {
        this.name = name;
        this.price = price;
        this.reviewsDto = reviewsDto;
        this.itemCategory = itemCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<ReviewDto> getReviews() {
        return reviewsDto;
    }

    public void setReviews(List<ReviewDto> reviewsDto) {
        this.reviewsDto = reviewsDto;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategoryDto) {
        this.itemCategory = itemCategoryDto;
    }
}
