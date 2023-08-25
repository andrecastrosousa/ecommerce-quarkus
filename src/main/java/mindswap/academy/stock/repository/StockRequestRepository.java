package mindswap.academy.stock.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.stock.model.StockRequest;

@ApplicationScoped
public class StockRequestRepository implements PanacheRepository<StockRequest> {


}
