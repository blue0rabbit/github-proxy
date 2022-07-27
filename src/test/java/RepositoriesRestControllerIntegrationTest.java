import blue.rabbit.controllers.RepositoriesController;
import blue.rabbit.rest.client.GithubApi;
import blue.rabbit.services.UserRepositoriesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RepositoriesController.class, UserRepositoriesService.class})
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class RepositoriesRestControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepositoriesService userRepositoriesService;

    @Test
    public void repositoriesRestControllerIntegrationTestHavingBadAccount() throws Exception {
        String notExistingAccount = "321039ld12dmdkasdasd921d";

        mockMvc.perform(get("/" + notExistingAccount )
                        .contentType("application/json")
                        .accept("application/json")
                        .header("Accept","application/json"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void repositoriesRestControllerIntegrationTestHavingGoodAccount() throws Exception {
        String legitUser = "blue0rabbit";

        mockMvc.perform(get("/" + legitUser)
                        .contentType("application/json")
                        .header("Accept","application/json"))
                .andExpect(status().isOk());
    }
}
