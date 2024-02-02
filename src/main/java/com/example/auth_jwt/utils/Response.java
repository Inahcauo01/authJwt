package com.example.auth_jwt.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {
    private String message;
    private T result;
    private List<CustomError> errors;
}
