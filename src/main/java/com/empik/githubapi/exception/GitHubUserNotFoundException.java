package com.empik.githubapi.exception;

public class GitHubUserNotFoundException extends RuntimeException {

    public GitHubUserNotFoundException(String login) {
        super("Could not find user with login " + login);
    }
}
