package mindswap.academy.authentication.converter;

import jakarta.enterprise.context.ApplicationScoped;
import mindswap.academy.authentication.dto.AuthRegisterDto;
import mindswap.academy.authentication.model.UserRepresentationBuilder;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

@ApplicationScoped
public class AuthConverter {

    public UserRepresentation toUserRepresentationFromRegisterDto(AuthRegisterDto authRegisterDto, List<String> roles) {
        return UserRepresentationBuilder.builder()
                .withUsername(authRegisterDto.getEmail())
                .withEmail(authRegisterDto.getEmail())
                .withFirstName(authRegisterDto.getFirstName())
                .withLastName(authRegisterDto.getLastName())
                .withRoles(roles)
                .build();
    }
}
