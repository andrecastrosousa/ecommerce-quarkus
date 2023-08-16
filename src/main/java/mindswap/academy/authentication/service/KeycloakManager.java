package mindswap.academy.authentication.service;

import org.keycloak.admin.client.Keycloak;

public interface KeycloakManager {
    void initKeycloak();
    void closeKeycloak();
    Keycloak getKeycloak();
}
