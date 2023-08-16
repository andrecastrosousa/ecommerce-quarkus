package mindswap.academy.authentication.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.authentication.dto.AuthDto;
import mindswap.academy.authentication.dto.AuthForgotPasswordDto;
import mindswap.academy.authentication.dto.AuthLoginDto;
import mindswap.academy.authentication.dto.AuthRegisterDto;
import mindswap.academy.user.repository.UserRepository;

@ApplicationScoped
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    UserRepository userRepository;

    @Inject
    KeycloakManagerImpl keycloakManagerImpl;

    @Override
    public AuthDto login(AuthLoginDto authLoginDto) {
        return null;
    }

    @Override
    public void register(AuthRegisterDto authRegisterDto) {

    }

    @Override
    public void forgotPassword(AuthForgotPasswordDto authForgotPasswordDto) {

    }
}
