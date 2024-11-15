package world.snowcrystal.template.domain.user.dto.command;

import jakarta.validation.constraints.Min;
import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

import java.io.Serial;

/**
 * 用户更新个人信息请求
 */
@Data
public class UserUpdateCommand implements Command {


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
    @Serial
    private static final long serialVersionUID = 1L;
}
