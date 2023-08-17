package mindswap.academy.item.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.item.model.Item;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {
}
