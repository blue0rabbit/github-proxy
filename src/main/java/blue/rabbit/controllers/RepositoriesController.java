package blue.rabbit.controllers;

import blue.rabbit.aspects.HeaderRequired;
import blue.rabbit.dtos.UserRepositoriesDataDTO;
import blue.rabbit.exceptions.HttpRuntimeException;
import blue.rabbit.services.UserRepositoriesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RepositoriesController {
    private final UserRepositoriesService userRepositoriesService;

    @GetMapping("/{username}")
    @HeaderRequired
    @ApiOperation(value = "Return user repositories with specified information")
    public List<UserRepositoriesDataDTO> getUserRepositories(@PathVariable("username") String username) throws HttpRuntimeException {
        List<UserRepositoriesDataDTO> userRepositoriesDataList
                = userRepositoriesService.getUserGithubRepositories(username);

        if (userRepositoriesDataList != null) {
            return userRepositoriesService.getUserGithubRepositories(username);
        } else {
            throw new HttpRuntimeException("User not exist", HttpStatus.NOT_FOUND);
        }
    }
}
