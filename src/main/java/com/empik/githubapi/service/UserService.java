package com.empik.githubapi.service;

import com.empik.githubapi.dto.UserResponse;

public interface UserService {

    UserResponse getUser(String login);
}
