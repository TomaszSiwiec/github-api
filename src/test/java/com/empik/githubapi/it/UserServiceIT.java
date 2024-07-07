package com.empik.githubapi.it;

import com.empik.githubapi.client.GitHubClient;
import com.empik.githubapi.client.GitHubUser;
import com.empik.githubapi.dto.UserResponse;
import com.empik.githubapi.exception.GitHubUserNotFoundException;
import com.empik.githubapi.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceIT {

    @MockBean
    private GitHubClient gitHubClient;

    @Autowired
    private UserService userService;

    @Test
    public void shouldReturnUserResponseForValidUser() {
        // given
        String login = "TomaszSiwiec";
        GitHubUser gitHubUser = exampleGitHubUser();
        Mockito.when(gitHubClient.getUser(login))
                .thenReturn(gitHubUser);

        // when
        UserResponse response = userService.getUser(login);

        // then
        assertEquals(gitHubUser.getId(), response.id());
        assertEquals(gitHubUser.getLogin(), response.login());
        assertEquals(gitHubUser.getName(), response.name());
        assertEquals(gitHubUser.getType(), response.type());
        assertEquals(gitHubUser.getAvatarUrl(), response.avatarUrl());
        assertEquals(gitHubUser.getCreatedAt(), response.createdAt());
        assertEquals(0.0, response.calculations());
    }

    @Test
    public void shouldThrowExceptionForInvalidUser() {
        // given
        String login = "TomaszSiwiec123";
        Mockito.when(gitHubClient.getUser(login))
                .thenThrow(new RuntimeException());

        // then
        assertThrows(GitHubUserNotFoundException.class, () -> userService.getUser(login));
    }

    private GitHubUser exampleGitHubUser() {
        return GitHubUser.builder()
                .id("123")
                .login("TomaszSiwiec")
                .name("Tomasz Siwiec")
                .avatarUrl("https://google.com/avatar")
                .followers(0)
                .publicRepos(2)
                .type("USER")
                .createdAt(Instant.now())
                .build();
    }
}
