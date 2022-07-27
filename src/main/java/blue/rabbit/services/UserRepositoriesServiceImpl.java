package blue.rabbit.services;

import blue.rabbit.dtos.BranchDataDTO;
import blue.rabbit.dtos.BranchesDTO;
import blue.rabbit.dtos.RepositoriesDTO;
import blue.rabbit.dtos.UserRepositoriesDataDTO;
import blue.rabbit.exceptions.HttpRuntimeException;
import blue.rabbit.rest.client.GithubApi;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRepositoriesServiceImpl implements UserRepositoriesService {
    private final GithubApi githubApi;

    @Override
    public List<UserRepositoriesDataDTO> getUserGithubRepositories(String username) {
        List<UserRepositoriesDataDTO> userRepositoriesDataSet = new ArrayList<>();
        Set<RepositoriesDTO> repositories;
        try {
            repositories = githubApi.getUserRepositories(username).execute().body();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new HttpRuntimeException("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (repositories != null) {
            if (repositories.isEmpty()) {
                try {
                    boolean successfulResponse = githubApi.checkIfUserExists(username).execute().isSuccessful();
                    if (successfulResponse) {
                        return List.of();
                    } else {
                        return null;
                    }
                } catch (IOException e) {
                    log.error(e.getMessage());
                    throw new HttpRuntimeException("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {

                UserRepositoriesDataDTO userRepositoriesDataDTO = new UserRepositoriesDataDTO();

                repositories.stream().filter(RepositoriesDTO::isFork).forEach(
                        repositoriesDTO -> {
                            userRepositoriesDataDTO.setUsername(username);
                            userRepositoriesDataDTO.setRepositoryName(repositoriesDTO.getName());
                            Set<BranchesDTO> branches;
                            try {
                                branches = githubApi.getBranchesForRepository(username, repositoriesDTO.getName()).execute().body();
                                if (branches != null) {
                                    branches.forEach(branchesDTO ->
                                            userRepositoriesDataDTO.getBranchDataSet().add(new BranchDataDTO(branchesDTO.getName(),
                                                    branchesDTO.getCommit().getSha()))
                                    );
                                }
                            } catch (IOException e) {
                                log.error(e.getMessage());
                                throw new HttpRuntimeException("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                            userRepositoriesDataSet.add(userRepositoriesDataDTO);

                        });
            }
        }
        return userRepositoriesDataSet;
    }

}
