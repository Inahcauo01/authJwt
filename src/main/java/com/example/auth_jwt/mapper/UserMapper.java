package com.example.auth_jwt.mapper;

import com.example.auth_jwt.domain.User;
import com.example.auth_jwt.dto.responce.UserDtoResponse;
import com.example.auth_jwt.dto.RoleDto;
import com.example.auth_jwt.dto.UserDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
public class UserMapper {
    private UserMapper() {}

    public static UserDtoResponse toDto(User user){
        return UserDtoResponse.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(user.getAuthorities().stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .enabled(user.isEnabled())
                .build();
    }

    public static User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(userDto.getPassword())
                .enabled(userDto.isEnabled())
                .authorities(
                        Set.of(
                                RoleMapper.toEntity(
                                        RoleDto.builder()
                                                .authority("ROLE_USER")
                                                .build()
                                )
                        )
                )
                .build();
    }

}
