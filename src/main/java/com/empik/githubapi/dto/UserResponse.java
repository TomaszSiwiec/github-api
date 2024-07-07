package com.empik.githubapi.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record UserResponse(
        String id,
        String login,
        String name,
        String type,
        String avatarUrl,
        Instant createdAt,
        Double calculations
) {
}
