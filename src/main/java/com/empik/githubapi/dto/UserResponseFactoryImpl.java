package com.empik.githubapi.dto;

import com.empik.githubapi.client.GitHubUser;
import com.empik.githubapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserResponseFactoryImpl implements UserResponseFactory {

    private final UserMapper userMapper;

    @Override
    public UserResponse createUserResponse(GitHubUser gitHubUser) {
        return userMapper.mapToResponse(gitHubUser);
    }

}
