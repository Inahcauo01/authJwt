package com.example.auth_jwt.security;

import com.example.auth_jwt.domain.Role;
import com.example.auth_jwt.repository.RoleRepository;
import com.example.auth_jwt.repository.UserRepository;
import com.example.auth_jwt.auth.AuthenticationResponse;
import com.example.auth_jwt.auth.LoginRequest;
import com.example.auth_jwt.auth.RegisterRequest;
import com.example.auth_jwt.domain.User;
import com.example.auth_jwt.service.UserService;
import com.example.auth_jwt.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationResponse register(RegisterRequest request) throws ValidationException {
        // create a user out of the request
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .build();

        // Set the saved roles to the user
        user.setAuthorities(getOrCreateRoles(request.getRoles()));


        userService.save(user);

        // generate & return a token for the user
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) throws ValidationException {
        // authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        // find the user by username
        var user = userService.findByUsername(request.getUsername());
        // generate & return token for the user
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    private Set<Role> getOrCreateRoles(Set<String> roleNames) {
        return roleNames.stream()
                .map(this::getOrCreateRole)
                .collect(Collectors.toSet());
    }

    private Role getOrCreateRole(String roleName) {
        // Check if the role exists
        return roleRepository.findByAuthority(roleName)
                .orElseGet(() -> roleRepository.save(Role.builder().authority(roleName).build()));
    }
}
