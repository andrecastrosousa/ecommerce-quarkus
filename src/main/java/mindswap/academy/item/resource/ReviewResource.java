package mindswap.academy.item.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.item.dto.ReviewCreateDto;
import mindswap.academy.item.dto.ReviewDto;
import mindswap.academy.item.model.Review;
import mindswap.academy.item.service.ReviewService;

@Path("/review")
public class ReviewResource {

    @Inject
    ReviewService reviewService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ReviewDto addReview(ReviewCreateDto reviewCreateDto){
        return reviewService.addReview(reviewCreateDto);
    }




}
