package mindswap.academy.authentication.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import mindswap.academy.authentication.model.Auth;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;

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

    @ConfigProperty(name = "authz-client.client-id")
    private String authzClientId;

    @ConfigProperty(name = "authz-client.secret")
    private String authzSecret;

    private Keycloak keycloak;

    private AuthzClient authzClient;

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

        authzClient = AuthzClient.create(
                new Configuration(
                        serverUrl,
                        innerRealm,
                        authzClientId,
                        Collections.singletonMap("secret", authzSecret),
                        HttpClients.createDefault()
                )
        );
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
        try {
            String token = authzClient
                    .obtainAccessToken(auth.getEmail(), auth.getPassword())
                    .getToken();
            auth.setToken(token);
            return auth;
        } catch (Exception e) {
            throw new IllegalArgumentException("Token can't be obtained", e);
        }
    }
}
