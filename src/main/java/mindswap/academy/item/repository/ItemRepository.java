package mindswap.academy.item.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import mindswap.academy.item.model.Item;

public class ItemRepository implements PanacheRepository<Item> {
}
