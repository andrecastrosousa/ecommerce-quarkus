package mindswap.academy.user.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.user.dto.UserCreateDto;
import mindswap.academy.user.dto.UserDto;
import mindswap.academy.user.dto.UserUpdateDto;
import mindswap.academy.user.model.User;
@ApplicationScoped
public class UserConverter {

    @Inject
    ObjectMapper objectMapper;

    public UserDto toDto(User user){
        return objectMapper.convertValue(user, UserDto.class);
    }

    public User toEntityFromCreateDto(UserCreateDto userCreateDto){
        return User.builder()
                .withPhoneNumber(userCreateDto.getPhoneNumber())
                .withEmail(userCreateDto.getEmail())
                .withNif(userCreateDto.getNif())
                .withCitizenCard(userCreateDto.getCitizenCard())
                .withAddress(userCreateDto.getAddresses())
                .build();
    }

    public User toEntityFromUpdateDto(UserUpdateDto userUpdateDto){
                return User.builder()
                        .withPhoneNumber(userUpdateDto.getPhoneNumber())
                        .withId(userUpdateDto.getId())
                        .build();
    }

    public User fromDto(UserDto userDto){
        return objectMapper.convertValue(userDto, User.class);
    }
}
