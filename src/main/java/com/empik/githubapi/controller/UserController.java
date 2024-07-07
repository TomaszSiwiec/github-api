package com.empik.githubapi.controller;

import com.empik.githubapi.dto.UserResponse;
import com.empik.githubapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{login}")
    @Operation(operationId = "Get user information", description = "Fetches user information from GitHub and calculates additional data")
    public UserResponse getUser(@PathVariable String login) {
        return userService.getUser(login);
    }

}
