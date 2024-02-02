package com.example.auth_jwt.service.impl;

import com.example.auth_jwt.domain.User;
import com.example.auth_jwt.repository.UserRepository;
import com.example.auth_jwt.service.RoleService;
import com.example.auth_jwt.service.UserService;
import com.example.auth_jwt.utils.CustomError;
import com.example.auth_jwt.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) throws ValidationException {
        return userRepository.findById(id).orElseThrow(
                    () -> new ValidationException(new CustomError("id", "User not found")));
    }

    @Override
    public User save(User user) throws ValidationException {
        // validation
        validate(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getAuthorities() == null || user.getAuthorities().isEmpty())
            user.setAuthorities(Set.of(roleService.findByAuthority("ROLE_USER"))); //default role
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        // TODO: validation
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) throws ValidationException {
        findById(id);
        userRepository.deleteById(id);
    }


    public User findByUsername(String assignedTo) throws ValidationException {
        return userRepository.findByUsername(assignedTo).orElseThrow(
                () -> new ValidationException(new CustomError("username", "User with username " + assignedTo + " not found"))
        );
    }


    public void validate(User user) throws ValidationException {
        if (user.getUsername() == null || user.getUsername().isBlank())
            throw new ValidationException(new CustomError("username", "Username is required"));
        if (user.getPassword() == null || user.getPassword().isBlank())
            throw new ValidationException(new CustomError("password", "Password is required"));
        if (user.getFirstName() == null || user.getFirstName().isBlank())
            throw new ValidationException(new CustomError("firstName", "First name is required"));
        if (user.getLastName() == null || user.getLastName().isBlank())
            throw new ValidationException(new CustomError("lastName", "Last name is required"));
        if (user.getAuthorities() == null || user.getAuthorities().isEmpty())
            throw new ValidationException(new CustomError("authorities", "Authorities are required"));
    }
}
