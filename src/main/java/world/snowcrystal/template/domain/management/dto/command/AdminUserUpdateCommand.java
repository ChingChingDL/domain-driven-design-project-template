package world.snowcrystal.template.domain.management.dto.command;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

import java.io.Serial;

/**
 * 用户更新请求
 */
@Data
public class AdminUserUpdateCommand implements Command {
    /**
     * id
     */
    @Nonnull
    @Min(0)
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 简介
     */
    private String profile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;
    @Serial
    private static final long serialVersionUID = 1L;
}
