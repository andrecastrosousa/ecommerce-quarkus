package mindswap.academy.supplier.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.supplier.model.Supplier;

@ApplicationScoped
public class SupplierRepository implements PanacheRepository<Supplier> {
}
