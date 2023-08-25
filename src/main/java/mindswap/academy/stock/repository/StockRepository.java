package mindswap.academy.stock.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.stock.model.Stock;

@ApplicationScoped
public class StockRepository implements PanacheRepository<Stock> {

    public Stock findBySupplierIdAndItemId(Long supplierId, Long itemId){
        return find(
                "supplier.id = :supplierId and item.id = :itemId",
                Parameters.with("supplierId", supplierId)
                    .and("itemId", itemId)
        ).firstResultOptional().orElse(null);
    }
}
