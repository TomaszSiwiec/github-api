package com.empik.githubapi.exception;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ExceptionResponseBody(
        LocalDateTime timestamp,
        int status,
        String error,
        String path,
        String message) {
}
