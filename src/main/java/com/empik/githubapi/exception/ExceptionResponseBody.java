package com.empik.githubapi.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponseBody(
        LocalDateTime timestamp,
        int status,
        String error,
        String path,
        String message
) {
}
