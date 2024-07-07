package com.empik.githubapi.dto;

import com.empik.githubapi.client.GitHubUser;
import com.empik.githubapi.mapper.UserMapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class UserResponseFactoryImpl implements UserResponseFactory {

    private final UserMapper userMapper;

    @Override
    public UserResponse createUserResponse(GitHubUser gitHubUser) {
        return userMapper.mapToResponse(gitHubUser);
    }

}
