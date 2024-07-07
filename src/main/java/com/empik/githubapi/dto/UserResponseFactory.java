package com.empik.githubapi.dto;

import com.empik.githubapi.client.GitHubUser;

public interface UserResponseFactory {

    UserResponse createUserResponse(GitHubUser gitHubUser);

}
