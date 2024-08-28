package ru.auth.server.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.auth.server.dto.UserDto;
import ru.auth.server.entities.AuthUser;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.login", target = "login")
    @Mapping(source = "token", target = "token")
    @Mapping(target = "password", ignore = true)
    UserDto toUserDto(AuthUser user, String token);

    @Mapping(source = "encodedPassword", target = "password")
    AuthUser toAuthUser(UserDto userDto, String encodedPassword);
}
