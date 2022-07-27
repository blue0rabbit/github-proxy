package blue.rabbit.services;

import blue.rabbit.dtos.UserRepositoriesDataDTO;

import java.util.List;

public interface UserRepositoriesService {
    List<UserRepositoriesDataDTO> getUserGithubRepositories(String username);
}
