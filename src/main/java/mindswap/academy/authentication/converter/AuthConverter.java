package mindswap.academy.authentication.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.authentication.dto.AuthDto;
import mindswap.academy.authentication.dto.AuthLoginDto;
import mindswap.academy.authentication.dto.AuthRegisterDto;
import mindswap.academy.authentication.model.Auth;
import mindswap.academy.authentication.model.UserRepresentationBuilder;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

@ApplicationScoped
public class AuthConverter {

    @Inject
    ObjectMapper objectMapper;

    public UserRepresentation toUserRepresentationFromRegisterDto(AuthRegisterDto authRegisterDto, List<String> roles) {
        return UserRepresentationBuilder.builder()
                .withUsername(authRegisterDto.getEmail())
                .withEmail(authRegisterDto.getEmail())
                .withFirstName(authRegisterDto.getFirstName())
                .withLastName(authRegisterDto.getLastName())
                .withRoles(roles)
                .build();
    }

    public AuthDto toDto(Auth auth) {
        return objectMapper.convertValue(auth, AuthDto.class);
    }

    public Auth toEntityFromLoginDto(AuthLoginDto authLoginDto) {
        return objectMapper.convertValue(authLoginDto, Auth.class);
    }
}
