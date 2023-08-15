package mindswap.academy.user.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import mindswap.academy.user.dto.UserCreateDto;
import mindswap.academy.user.dto.UserDto;
import mindswap.academy.user.dto.UserUpdateDto;
import mindswap.academy.user.service.UserService;

import java.util.List;

@Path("/users")
public class UserResource {
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDto> get(){
        return userService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto getId(@PathParam("id") Long id){
        return userService.getById(id);
    }
    @PUT
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public UserDto put(@PathParam("userId") Long userId, UserUpdateDto userUpdateDto){
        return userService.update(userId, userUpdateDto);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public UserDto post(UserCreateDto userDto) {
        return userService.create(userDto);
    }

    @DELETE
    @Path("{userId}")
    @Transactional
    public void delete(@PathParam("userId") Long userId){
        userService.delete(userId);
    }


}

