package mindswap.academy.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.model.Order;


@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {
}
