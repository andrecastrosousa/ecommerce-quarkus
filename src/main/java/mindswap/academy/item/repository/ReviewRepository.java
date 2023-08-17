package mindswap.academy.item.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.item.model.Review;

@ApplicationScoped
public class ReviewRepository implements PanacheRepository<Review> {
}
