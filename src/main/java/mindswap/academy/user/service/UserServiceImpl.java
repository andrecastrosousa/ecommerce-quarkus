package mindswap.academy.user.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.user.converter.UserConverter;
import mindswap.academy.user.dto.UserCreateDto;
import mindswap.academy.user.dto.UserDto;
import mindswap.academy.user.dto.UserUpdateDto;
import mindswap.academy.user.model.User;
import mindswap.academy.user.repository.UserRepository;
import mindswap.academy.user.service.UserService;

import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserConverter userConverter;

    @Override
    public List<UserDto> getAll() {
        return userRepository.listAll()
                .stream()
                .map(user -> userConverter.toDto(user))
                .toList();
    }

    @Override
    public UserDto getById(Long id){
        User user = userRepository.findById(id);
        if(user == null){
            throw new WebApplicationException("User not found", 404);
        }
        return userConverter.toDto(user);
    }

    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        User userFound = userRepository.findById(id);
        User user = userConverter.toEntityFromUpdateDto(userUpdateDto);
        if(userFound == null || !user.getId().equals(id)){
            throw new WebApplicationException("User not found", 404);
        }
        userFound.setPhoneNumber(user.getPhoneNumber());
        userRepository.persist(userFound);
        return userConverter.toDto(userFound);
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        User user = userConverter.toEntityFromCreateDto(userCreateDto);
        userRepository.persist(user);
        return userConverter.toDto(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id);
        if (user == null){
            throw new WebApplicationException("User not found", 404);
        }
        userRepository.delete(user);
    }
}
