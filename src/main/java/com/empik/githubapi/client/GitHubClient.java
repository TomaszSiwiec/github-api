package com.empik.githubapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gitHubClient", url = "https://api.github.com")
public interface GitHubClient {

    @GetMapping("/users/{login}")
    GitHubUser getUser(@PathVariable String login);
}
