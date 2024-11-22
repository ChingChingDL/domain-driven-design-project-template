package world.snowcrystal.template.domain.like.dto.command;

import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

@Data
public class PostLikeCancelCommand implements Command {
    private Long postId;
}
