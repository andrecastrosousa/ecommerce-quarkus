package mindswap.academy.item.converter;

import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.dto.ReviewCreateDto;
import mindswap.academy.item.dto.ReviewDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;
import mindswap.academy.item.model.Review;

@ApplicationScoped
public class ReviewConverter {

    public Review toEntityFromCreateDto(ReviewCreateDto reviewCreateDto, Item item){
        return new Review(reviewCreateDto.getRating(), reviewCreateDto.getCommentary(), item);
    }

    public ReviewDto toDto(Review review){
        return new ReviewDto(review.getRating(),review.getCommentary());
    }
}
