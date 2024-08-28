package ru.auth.server.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.auth.server.dto.UserDto;
import ru.auth.server.dto.UserDto.UserDtoBuilder;
import ru.auth.server.entities.AuthUser;
import ru.auth.server.entities.AuthUser.AuthUserBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-28T21:51:42+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(AuthUser user, String token) {
        if ( user == null && token == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        if ( user != null ) {
            if ( user.getId() != null ) {
                userDto.id( user.getId() );
            }
            userDto.login( user.getLogin() );
        }
        if ( token != null ) {
            userDto.token( token );
        }

        return userDto.build();
    }

    @Override
    public AuthUser toAuthUser(UserDto userDto, String encodedPassword) {
        if ( userDto == null && encodedPassword == null ) {
            return null;
        }

        AuthUserBuilder authUser = AuthUser.builder();

        if ( userDto != null ) {
            authUser.id( userDto.getId() );
            authUser.login( userDto.getLogin() );
        }
        if ( encodedPassword != null ) {
            authUser.password( encodedPassword );
        }

        return authUser.build();
    }
}
