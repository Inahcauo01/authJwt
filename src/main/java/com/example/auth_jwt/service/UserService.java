package com.example.auth_jwt.service;

import com.example.auth_jwt.domain.User;
import com.example.auth_jwt.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();
    User findById(Long id) throws ValidationException;
    User save(User user) throws ValidationException;
    User update(User user);
    void deleteById(Long id) throws ValidationException;

    User findByUsername(String assignedTo) throws ValidationException;

}
