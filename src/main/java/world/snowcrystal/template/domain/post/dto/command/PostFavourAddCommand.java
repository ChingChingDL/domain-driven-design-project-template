package world.snowcrystal.template.domain.post.dto.command;

import jakarta.validation.constraints.Min;
import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

import java.io.Serial;

/**
 * 文章收藏 / 取消收藏请求
 */
@Data
public class PostFavourAddCommand implements Command {

    /**
     * 文章 id
     */
    @Min(0)
    private Long postId;
    @Serial
    private static final long serialVersionUID = 1L;
}
