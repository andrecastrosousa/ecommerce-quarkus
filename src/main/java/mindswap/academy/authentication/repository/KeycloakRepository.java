package mindswap.academy.authentication.repository;

import mindswap.academy.authentication.model.Auth;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakRepository {
    void initKeycloak();
    void closeKeycloak();
    String createAuthentication(UserRepresentation userRepresentation, String password);
    Auth login(Auth auth);
}
