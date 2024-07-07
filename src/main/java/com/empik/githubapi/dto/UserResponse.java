package com.empik.githubapi.dto;

import java.time.Instant;

import lombok.Builder;

@Builder
public record UserResponse(
        String id,
        String login,
        String name,
        String type,
        String avatarUrl,
        Instant createdAt,
        Double calculations) {
}
