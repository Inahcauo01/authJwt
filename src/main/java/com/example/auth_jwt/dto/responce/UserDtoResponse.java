package com.example.auth_jwt.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoResponse {
    private String firstName;
    private String lastName;
    private String username;
    private boolean enabled = true;

    private Set<String> roles;

    private Integer jetons;
}
