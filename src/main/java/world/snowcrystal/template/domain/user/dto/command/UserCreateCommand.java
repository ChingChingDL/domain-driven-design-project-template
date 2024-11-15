package world.snowcrystal.template.domain.user.dto.command;

import jakarta.annotation.Nonnull;
import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

import java.io.Serial;

/**
 * 用户创建请求
 */
@Data
public class UserCreateCommand implements Command {

    /**
     * 用户昵称
     */
    @Nonnull
    private String username;

    /**
     * 账号
     */
    @Nonnull
    private String account;

    /**
     * 用户头像
     */
    @Nonnull
    private String avatar;

    /**
     * 用户角色: user, admin
     */
    private String role;
    @Serial
    private static final long serialVersionUID = 1L;
}
