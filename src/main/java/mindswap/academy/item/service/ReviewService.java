package mindswap.academy.item.service;

import mindswap.academy.item.dto.ReviewCreateDto;
import mindswap.academy.item.dto.ReviewDto;
import mindswap.academy.item.model.Review;

public interface ReviewService {
    ReviewDto addReview(ReviewCreateDto reviewCreateDto);
    void removeReview(Long id);
}
