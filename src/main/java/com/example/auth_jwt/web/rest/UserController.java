package com.example.auth_jwt.web.rest;

import com.example.auth_jwt.dto.UserDto;
import com.example.auth_jwt.dto.responce.UserDtoResponse;
import com.example.auth_jwt.mapper.UserMapper;
import com.example.auth_jwt.service.UserService;
import com.example.auth_jwt.utils.Response;
import com.example.auth_jwt.utils.ValidationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Response<List<UserDtoResponse>>> getAllUsers(){
        Response<List<UserDtoResponse>> response = new Response<>();
        List<UserDtoResponse> userList = userService.findAll().stream().map(UserMapper::toDto).toList();
        response.setResult(userList);
        if (userList.isEmpty())
            response.setMessage("There are no users");
        return ResponseEntity.ok().body(response);
    }


}
