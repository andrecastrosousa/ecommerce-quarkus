package mindswap.academy.authentication.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mindswap.academy.authentication.converter.AuthConverter;
import mindswap.academy.authentication.dto.AuthDto;
import mindswap.academy.authentication.dto.AuthForgotPasswordDto;
import mindswap.academy.authentication.dto.AuthLoginDto;
import mindswap.academy.authentication.dto.AuthRegisterDto;
import mindswap.academy.authentication.model.Auth;
import mindswap.academy.authentication.repository.KeycloakRepository;
import mindswap.academy.user.model.User;
import mindswap.academy.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    UserRepository userRepository;

    @Inject
    KeycloakRepository keycloakRepository;

    @Inject
    AuthConverter authConverter;

    @Override
    public AuthDto login(AuthLoginDto authLoginDto) {
        Auth auth = keycloakRepository.login(authConverter.toEntityFromLoginDto(authLoginDto));
        return authConverter.toDto(auth);
    }

    @Override
    @Transactional
    public void register(AuthRegisterDto authRegisterDto) {
        List<String> roles = new ArrayList<>(List.of("user"));
        String authId = keycloakRepository.createAuthentication(
                authConverter.toUserRepresentationFromRegisterDto(authRegisterDto, roles),
                authRegisterDto.getPassword()
        );

        User user = authConverter.toUserFromRegisterDto(authRegisterDto);
        user.setAuthId(authId);
        userRepository.persist(user);
    }

    @Override
    public void forgotPassword(AuthForgotPasswordDto authForgotPasswordDto) {

    }
}
