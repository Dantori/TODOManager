package ru.trofimov.todomanager.web.mapper;

import org.mapstruct.Mapper;
import ru.trofimov.todomanager.domain.account.User;
import ru.trofimov.todomanager.web.dto.UserDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    List<UserDto> toDto(List<User> users);
    User toEntity(UserDto userDto);
}
