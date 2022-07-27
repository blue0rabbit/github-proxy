package blue.rabbit.dtos;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "Final and prepared branch information")
public class BranchDataDTO {
    private String name;
    private String sha;
}
