package blue.rabbit.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApiModel(value = "Custom error body")
public class ErrorDTO {
    @JsonProperty("status")
    private int status;
    @JsonProperty("Message")
    private String message;
}
