package com.empik.githubapi.service.impl;

import com.empik.githubapi.client.GitHubClient;
import com.empik.githubapi.client.GitHubUser;
import com.empik.githubapi.dto.UserResponse;
import com.empik.githubapi.dto.UserResponseFactory;
import com.empik.githubapi.entity.User;
import com.empik.githubapi.exception.GitHubUserNotFoundException;
import com.empik.githubapi.repository.UserRepository;
import com.empik.githubapi.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final GitHubClient gitHubClient;
    private final UserResponseFactory userResponseFactory;

    @Override
    @Transactional
    public UserResponse getUser(String login) {
        try {
            GitHubUser gitHubUser = gitHubClient.getUser(login);
            User user = userRepository.findOrCreateByLogin(login);
            user.incrementRequestCount();
            LOGGER.info("Successfully fetched user information for login: {}", login);
            return userResponseFactory.createUserResponse(gitHubUser);
        } catch (RuntimeException ex) {
            LOGGER.error("Error fetching user information for login: {}", login, ex);
            throw new GitHubUserNotFoundException(ex.getMessage());
        }
    }
}
