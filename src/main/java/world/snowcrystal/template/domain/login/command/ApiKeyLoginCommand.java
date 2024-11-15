package world.snowcrystal.template.domain.login.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import world.snowcrystal.template.domain.common.command.Command;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class ApiKeyLoginCommand implements Command {
    @NotBlank
    @Size(max = 50)
     String apiKey;

    @NotBlank
    @Size(max = 50)
     String apiSecret;

}
