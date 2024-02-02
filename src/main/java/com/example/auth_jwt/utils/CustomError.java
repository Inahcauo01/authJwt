package com.example.auth_jwt.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomError {
    private final String field;
    private final String message;
}
