package mindswap.academy.item.converter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.dto.ItemDto;
import mindswap.academy.item.dto.ReviewDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;
import mindswap.academy.item.model.Review;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItemConverter {

    public Item toEntityFromCreateDto(ItemCreateDto itemCreateDto, ItemCategory itemCategory){
        return Item.builder()
                .withName(itemCreateDto.getName())
                .withPrice(itemCreateDto.getPrice())
                .withItemCategory(itemCategory)
                .withDescription(itemCreateDto.getDescription())
                .build();
    }
    public ItemDto toDto(Item item){
        return new ItemDto(item.getName(), item.getPrice(), item.getItemCategory().getName(), reviewToreviewDtos(item.getReviews()));
    }

    public List<ReviewDto> reviewToreviewDtos (List<Review> reviews){
        List<ReviewDto> reviewsDto = new ArrayList<>();
        if(reviews!=null){
            reviews.forEach(r->reviewsDto.add(new ReviewDto(r.getRating(),r.getCommentary())));
            return reviewsDto;
        }
        return null;
    }



}
