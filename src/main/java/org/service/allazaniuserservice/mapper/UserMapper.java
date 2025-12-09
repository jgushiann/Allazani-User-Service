package org.service.allazaniuserservice.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.service.allazaniuserservice.dto.UserRequestDto;
import org.service.allazaniuserservice.dto.UserResponseDto;
import org.service.allazaniuserservice.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
    User toEntity(UserResponseDto userDto);
    User updateEntity(UserRequestDto dto, @MappingTarget User entity);
}
