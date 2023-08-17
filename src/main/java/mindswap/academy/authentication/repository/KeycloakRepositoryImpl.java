package mindswap.academy.authentication.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

@ApplicationScoped
public class KeycloakRepositoryImpl implements KeycloakRepository {
    @ConfigProperty(name = "keycloak.realm")
    private String realm;

    @ConfigProperty(name = "keycloak.clientId")
    private String clientId;

    @ConfigProperty(name = "keycloak.grantType")
    private String grantType;

    @ConfigProperty(name = "keycloak.username")
    private String username;

    @ConfigProperty(name = "keycloak.password")
    private String password;

    @Inject
    private Keycloak keycloak;

    @PostConstruct
    public void initKeycloak() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/realms/quarkus")
                .realm(realm)
                .clientId(clientId)
                .grantType(grantType)
                .username(username)
                .password(password)
                .clientSecret("secret")
                .build();
    }

    @PreDestroy
    public void closeKeycloak() {
        keycloak.close();
    }

    @Override
    public String createAuthentication(UserRepresentation userRepresentation, String password) {
        // keycloak.tokenManager().grantToken().getToken();
        UsersResource usersResource = keycloak.realm(realm).users();
        var alo2 = keycloak.tokenManager().getAccessToken();

        Response response = usersResource.create(userRepresentation);
        String userId = CreatedResponseUtil.getCreatedId(response);

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);

        UserResource userResource = keycloak.realm(realm).users().get(userId);

        userResource.resetPassword(passwordCred);

        return userId;
    }
}
