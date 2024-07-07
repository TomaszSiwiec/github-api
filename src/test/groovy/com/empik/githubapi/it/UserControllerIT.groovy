package com.empik.githubapi.it

import com.empik.githubapi.controller.UserController
import com.empik.githubapi.dto.UserResponse
import com.empik.githubapi.exception.GitHubUserNotFoundException
import com.empik.githubapi.service.UserService
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import java.time.Instant

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ImportAutoConfiguration([FeignAutoConfiguration.class])
@WebMvcTest(controllers = [UserController.class])
class UserControllerIT extends Specification {

    @Autowired
    MockMvc mvc

    @MockBean
    UserService userService

    def "should return correct data when user exists"() {
        given:
        def login = "TomaszSiwiec"
        def path = String.format("/users/%s", login)
        Mockito.when(userService.getUser(login)).thenReturn(exampleUserResponse())

        when:
        def request = MockMvcRequestBuilders.get(path)
                .contentType(MediaType.APPLICATION_JSON)
        then:
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.login').value("TomaszSiwiec"))
    }

    def "should return status 404 when user does not exist"() {
        given:
        def login = "TomaszSiwiec123"
        def errorMessage = String.format("Could not find user with login %s", login)
        def path = String.format("/users/%s", login)
        Mockito.when(userService.getUser(login)).thenThrow(new GitHubUserNotFoundException(login))

        when:
        def request = MockMvcRequestBuilders.get(path)
                .contentType(MediaType.APPLICATION_JSON)
        then:
        mvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath('$.message').value(errorMessage))

    }

    private static UserResponse exampleUserResponse() {
        return UserResponse.builder()
                .id("123")
                .login("TomaszSiwiec")
                .name("Tomasz Siwiec")
                .avatarUrl("https://google.com/avatar")
                .type("USER")
                .createdAt(Instant.now())
                .calculations(0.0)
                .build()
    }
}