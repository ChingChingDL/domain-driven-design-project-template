package world.snowcrystal.template.domain.post.dto.command;

import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

import java.io.Serial;

/**
 * 文章点赞请求
 *
 *
 *
 */
@Data
public class PostThumbAddCommand implements Command {

    /**
     * 文章 id
     */
    private Long postId;
    @Serial
    private static final long serialVersionUID = 1L;
}
