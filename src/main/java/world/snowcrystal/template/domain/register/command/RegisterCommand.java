package world.snowcrystal.template.domain.register.command;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

import java.io.Serial;

/**
 * 用户注册请求体
 */
@Data
public class RegisterCommand implements Command {
    @Serial
    private static final long serialVersionUID = 3191241716373120793L;

    // 4 ~ 16 个字符
    // 只能包含字母、数字、下划线
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "账号长度为 4~16 个字符，只能包含字母、数字、下划线")
    private String account;

    private String password;

    private String checkPassword;
}
