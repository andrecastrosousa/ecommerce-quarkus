package mindswap.academy.order.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.order.model.OrderItem;

@ApplicationScoped
public class OrderItemRepository implements PanacheRepository<OrderItem> {
}
