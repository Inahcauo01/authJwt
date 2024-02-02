package com.example.auth_jwt.web.rest;


import com.example.auth_jwt.auth.AuthenticationResponse;
import com.example.auth_jwt.auth.LoginRequest;
import com.example.auth_jwt.auth.RegisterRequest;
import com.example.auth_jwt.security.AuthenticationService;
import com.example.auth_jwt.utils.Response;
import com.example.auth_jwt.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Response<AuthenticationResponse>> register(@RequestBody RegisterRequest request) throws ValidationException {
        Response<AuthenticationResponse> response = new Response<>();
        AuthenticationResponse authenticationResponse;
        try {
            authenticationResponse = authenticationService.register(request);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        response.setMessage("register successful");
        response.setResult(authenticationResponse);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<AuthenticationResponse>> login(@RequestBody LoginRequest request) throws ValidationException {
        Response<AuthenticationResponse> response = new Response<>();
        AuthenticationResponse authenticationResponse;
        try {
            authenticationResponse = authenticationService.login(request);
        }catch (Exception e){
            response.setMessage(e.getMessage()+" : Invalid username or password");
            return ResponseEntity.badRequest().body(response);
        }
        response.setMessage("Login successful");
        response.setResult(authenticationResponse);
        return ResponseEntity.ok().body(response);
    }

}
