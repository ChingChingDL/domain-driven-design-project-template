package world.snowcrystal.template.domain.register;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.register.command.RegisterCommand;
import world.snowcrystal.template.domain.register.command.RegisterCommandService;
import world.snowcrystal.template.domain.register.command.RegisterResponse;

import static org.springframework.http.HttpStatus.CREATED;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/registration")
public class RegisterController {

    private final RegisterCommandService registerCommandService;

    /**
     * 用户注册
     */
    @PostMapping
    @ResponseStatus(CREATED)
    public ApplicationResponse<RegisterResponse> userRegister(@Nonnull @RequestBody RegisterCommand registerCommand) {
        return ApplicationResponse.success(registerCommandService.userRegister(registerCommand));
    }


}
