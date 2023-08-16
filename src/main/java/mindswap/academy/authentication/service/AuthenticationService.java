package mindswap.academy.authentication.service;

import mindswap.academy.authentication.dto.AuthDto;
import mindswap.academy.authentication.dto.AuthForgotPasswordDto;
import mindswap.academy.authentication.dto.AuthLoginDto;
import mindswap.academy.authentication.dto.AuthRegisterDto;

public interface AuthenticationService {
    AuthDto login(AuthLoginDto authLoginDto);

    AuthDto register(AuthRegisterDto authRegisterDto);

    void forgotPassword(AuthForgotPasswordDto authForgotPasswordDto);
}
