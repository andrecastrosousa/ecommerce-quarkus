package mindswap.academy.item.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.item.converter.ReviewConverter;
import mindswap.academy.item.dto.ReviewCreateDto;
import mindswap.academy.item.dto.ReviewDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.model.ItemCategory;
import mindswap.academy.item.model.Review;
import mindswap.academy.item.repository.ItemRepository;
import mindswap.academy.item.repository.ReviewRepository;

@ApplicationScoped
public class ReviewServiceImpl implements ReviewService{
    @Inject
    ItemRepository itemRepository;
    @Inject
    ReviewRepository reviewRepository;
    @Inject
    ReviewConverter reviewConverter;

    @Override
    public ReviewDto addReview(ReviewCreateDto reviewCreateDto) {
        Long itemId = reviewCreateDto.getItemId();
        Item item = itemRepository.find("id",itemId)
                .firstResultOptional()
                .orElse(null);
        if(item==null){
            throw new WebApplicationException("Item doesn't exists",400);
        }
        Review review = reviewConverter.toEntityFromCreateDto(reviewCreateDto, item);
        reviewRepository.persist(review);
        return reviewConverter.toDto(review);

    }

    @Override
    public void removeReview(Long id) {

    }
}
