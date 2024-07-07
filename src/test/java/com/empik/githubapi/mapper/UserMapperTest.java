package com.empik.githubapi.mapper;

import com.empik.githubapi.client.GitHubUser;
import com.empik.githubapi.dto.UserResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    private final UserMapper userMapper = new UserMapperImpl();

    @Test
    void shouldMapGitHubUserToUserResponse() {
        // given
        GitHubUser gitHubUser = createGitHubUser();

        // when
        UserResponse userResponse = userMapper.mapToResponse(gitHubUser);

        // then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse).hasNoNullFieldsOrProperties();
        assertThat(userResponse.id()).isEqualTo(gitHubUser.getId());
        assertThat(userResponse.login()).isEqualTo(gitHubUser.getLogin());
        assertThat(userResponse.name()).isEqualTo(gitHubUser.getName());
        assertThat(userResponse.type()).isEqualTo(gitHubUser.getType());
        assertThat(userResponse.avatarUrl()).isEqualTo(gitHubUser.getAvatarUrl());
        assertThat(userResponse.createdAt()).isEqualTo(gitHubUser.getCreatedAt());
        assertThat(userResponse.calculations()).isEqualTo(0.0);
    }

    private GitHubUser createGitHubUser() {
        return GitHubUser.builder()
                .id("500")
                .login("TomaszSiwiec")
                .name("Tomasz Siwiec")
                .type("User")
                .avatarUrl("https://avatars.githubusercontent.com/u/500?v=3")
                .createdAt(Instant.ofEpochMilli(512))
                .followers(0)
                .publicRepos(5)
                .build();
    }
}
