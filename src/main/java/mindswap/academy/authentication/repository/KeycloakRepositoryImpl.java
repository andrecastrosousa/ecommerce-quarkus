package mindswap.academy.authentication.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import mindswap.academy.authentication.model.Auth;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

@ApplicationScoped
public class KeycloakRepositoryImpl implements KeycloakRepository {
    @ConfigProperty(name = "keycloak.baseRealm")
    private String baseRealm;

    @ConfigProperty(name = "keycloak.clientId")
    private String clientId;

    @ConfigProperty(name = "keycloak.grantType")
    private String grantType;

    @ConfigProperty(name = "keycloak.username")
    private String username;

    @ConfigProperty(name = "keycloak.password")
    private String password;

    @ConfigProperty(name = "keycloak.server-url")
    private String serverUrl;

    @ConfigProperty(name = "keycloak.innerRealm")
    private String innerRealm;

    @Inject
    private Keycloak keycloak;

    @PostConstruct
    public void initKeycloak() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(baseRealm)
                .clientId(clientId)
                .grantType(grantType)
                .username(username)
                .password(password)
                .build();
    }

    @PreDestroy
    public void closeKeycloak() {
        keycloak.close();
    }

    @Override
    public String createAuthentication(UserRepresentation userRepresentation, String password) {
        UsersResource usersResource = keycloak.realm(innerRealm).users();

        userRepresentation.setEnabled(true);

        Response response = usersResource.create(userRepresentation);
        String userId = CreatedResponseUtil.getCreatedId(response);

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);

        UserResource userResource = keycloak.realm(innerRealm).users().get(userId);

        userResource.resetPassword(passwordCred);

        return userId;
    }

    @Override
    public Auth login(Auth auth) {
        Keycloak keycloak1 = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(baseRealm)
                .clientId(clientId)
                .grantType(grantType)
                .username(auth.getEmail())
                .password(auth.getPassword())
                .build();

        auth.setToken(keycloak1.tokenManager().getAccessToken().getToken());

        return auth;
    }
}
