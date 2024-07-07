package com.empik.githubapi.mapper;

import com.empik.githubapi.client.GitHubUser;
import com.empik.githubapi.config.MapstructConfig;
import com.empik.githubapi.dto.UserResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapstructConfig.class)
public interface UserMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "avatarUrl", source = "avatarUrl")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "calculations", source = ".", qualifiedByName = "doCalculations")
    UserResponse mapToResponse(GitHubUser user);

    @Named("doCalculations")
    default Double doCalculations(GitHubUser gitHubUser) {
        Double divideByValue = Double.valueOf(gitHubUser.getFollowers() * (2 + gitHubUser.getPublicRepos()));
        if (divideByValue == 0) {
            return 0.0;
        }
        return 6 / divideByValue;
    }
}
