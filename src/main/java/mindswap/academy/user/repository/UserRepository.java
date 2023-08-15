package mindswap.academy.user.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.user.model.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
