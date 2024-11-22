package world.snowcrystal.template.domain.favorite.dto.command;

import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;


/**
 * 取消收藏文章；<谁>取消谁收藏了哪篇文章；
 */
@Data
public class PostFavourCancelCommand implements Command {
    private Long userId;
    private Long postId;
}
