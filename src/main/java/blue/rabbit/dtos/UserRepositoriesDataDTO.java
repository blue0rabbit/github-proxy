package blue.rabbit.dtos;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ApiModel(value = "Final information")
public class UserRepositoriesDataDTO {
    private String repositoryName;
    private String username;
    private Set<BranchDataDTO> branchDataSet = new HashSet<>();

}
