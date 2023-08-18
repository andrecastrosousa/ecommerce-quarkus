package mindswap.academy.authentication.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.authentication.dto.AuthDto;
import mindswap.academy.authentication.dto.AuthLoginDto;
import mindswap.academy.authentication.dto.AuthRegisterDto;
import mindswap.academy.authentication.service.AuthenticationService;
import org.apache.http.impl.client.HttpClients;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;

import java.util.Collections;

@Path("")
public class AuthenticationResource {

    @Inject
    AuthenticationService authenticationService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(AuthLoginDto authLoginDto) {
        final Configuration configuration = new Configuration("http://localhost:8081",
                "quarkus", // keycloak realm
                "backend-service", // keycloak client
                Collections.singletonMap("secret", "secret"),
                HttpClients.createDefault());

        try {

            return AuthzClient.create(configuration).obtainAccessToken(authLoginDto.getEmail(), authLoginDto.getPassword()).getToken(); // keycloak user and password
        } catch (Exception e) {
            throw new IllegalArgumentException("Token can't be obtained", e);
        }
        //return authenticationService.login(authLoginDto);
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void register(AuthRegisterDto authRegisterDto) {
        authenticationService.register(authRegisterDto);
    }
}
