package mindswap.academy.authentication.resource;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import mindswap.academy.authentication.dto.AuthDto;
import mindswap.academy.authentication.dto.AuthLoginDto;
import mindswap.academy.authentication.dto.AuthRegisterDto;
import mindswap.academy.authentication.service.AuthenticationService;

import java.security.Principal;

@Path("")
public class AuthenticationResource {

    @Inject
    AuthenticationService authenticationService;

    @Inject
    SecurityIdentity identity;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AuthDto login(AuthLoginDto authLoginDto) {
        return authenticationService.login(authLoginDto);
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void register(AuthRegisterDto authRegisterDto) {
        authenticationService.register(authRegisterDto);
    }
}
