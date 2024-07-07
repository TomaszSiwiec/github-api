package com.empik.githubapi.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUser {
    private String id;
    private String login;
    private String name;
    private String type;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("created_at")
    private Instant createdAt;

    @JsonProperty("followers")
    private int followers;

    @JsonProperty("public_repos")
    private int publicRepos;
}
