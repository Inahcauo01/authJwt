package com.example.auth_jwt.mapper;

import com.example.auth_jwt.domain.Role;
import com.example.auth_jwt.dto.RoleDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleMapper {
    private RoleMapper() {
    }

    public static RoleDto toDto(String role) {
        return RoleDto.builder()
                .authority(role)
                .build();
    }

    public static Role toEntity(RoleDto roleDto) {
        return Role.builder()
                .id(Role.builder()
                        .authority(roleDto.getAuthority())
                        .build()
                        .getId()
                )
                .authority(roleDto.getAuthority())
                .build();
    }
}
