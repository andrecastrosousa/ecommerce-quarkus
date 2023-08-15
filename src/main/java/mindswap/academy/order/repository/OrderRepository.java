package mindswap.academy.order.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.order.model.Order;


@ApplicationScoped
public class OrderRepository  implements PanacheRepository<Order> {
}
