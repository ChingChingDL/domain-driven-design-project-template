package world.snowcrystal.template.domain.like.dto.command;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;

@Builder
public class PostLikeCancelResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long totalLikes;
}
