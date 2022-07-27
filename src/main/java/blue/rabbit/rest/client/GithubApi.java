package blue.rabbit.rest.client;

import blue.rabbit.dtos.BranchesDTO;
import blue.rabbit.dtos.RepositoriesDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import java.util.Set;

public interface GithubApi {
    @GET("/users/{username}/repos")
    @Headers({"Accept: application/json"})
    Call<Set<RepositoriesDTO>> getUserRepositories(@Path("username") String username);

    @GET("/repos/{username}/{repository}/branches")
    @Headers({"Accept: application/json"})
    Call<Set<BranchesDTO>> getBranchesForRepository(@Path("username") String username,
                                                    @Path("repository") String repository);

    @HEAD("users/{username}")
    @Headers({"Accept: application/json"})
    Call<Void> checkIfUserExists(@Path("username") String username);
}

