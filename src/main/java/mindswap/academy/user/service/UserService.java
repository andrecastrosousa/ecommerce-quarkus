package mindswap.academy.user.service;

import mindswap.academy.user.dto.UserCreateDto;
import mindswap.academy.user.dto.UserDto;
import mindswap.academy.user.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();
    UserDto getById(Long id);
    UserDto update(Long id, UserUpdateDto userUpdateDto);

    UserDto create(UserCreateDto users);

    void delete(Long id);
}
