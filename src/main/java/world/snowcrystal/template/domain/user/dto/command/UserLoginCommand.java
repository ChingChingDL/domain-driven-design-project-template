package world.snowcrystal.template.domain.user.dto.command;

import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

import java.io.Serial;

/**
 * 用户登录请求
 */
@Data
public class UserLoginCommand implements Command {
    @Serial
    private static final long serialVersionUID = 3191241716373120793L;

    private String account;

    private String password;
}
