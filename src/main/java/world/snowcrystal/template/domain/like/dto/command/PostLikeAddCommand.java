package world.snowcrystal.template.domain.like.dto.command;

import jakarta.validation.constraints.Min;
import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

import java.io.Serial;

/**
 * 文章点赞请求
 */
@Data
public class PostLikeAddCommand implements Command {

    /**
     * 文章 id
     */
    @Min(0)
    private Long postId;

    @Min(0)
    private Long userId;

    @Serial
    private static final long serialVersionUID = 1L;
}
