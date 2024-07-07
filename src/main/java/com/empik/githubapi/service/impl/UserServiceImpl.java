package com.empik.githubapi.service.impl;

import com.empik.githubapi.client.GitHubClient;
import com.empik.githubapi.client.GitHubUser;
import com.empik.githubapi.dto.UserResponse;
import com.empik.githubapi.entity.User;
import com.empik.githubapi.exception.GitHubUserNotFoundException;
import com.empik.githubapi.mapper.UserMapper;
import com.empik.githubapi.repository.UserRepository;
import com.empik.githubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GitHubClient gitHubClient;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponse getUser(String login) {
        try {
            GitHubUser gitHubUser = gitHubClient.getUser(login);
            User user = userRepository.findOrCreateByLogin(login);
            user.incrementRequestCount();
            return userMapper.mapToResponse(gitHubUser);
        } catch (RuntimeException ex) {
            throw new GitHubUserNotFoundException(ex.getMessage());
        }

    }
}
