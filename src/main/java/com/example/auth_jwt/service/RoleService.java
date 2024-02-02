package com.example.auth_jwt.service;

import com.example.auth_jwt.domain.Role;
import com.example.auth_jwt.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface RoleService {
    Role save(Role role);
    List<Role> findAll();
    List<Role> saveAll(Collection<Role> roles);
    Role findByAuthority(String authority) throws ValidationException;

}
