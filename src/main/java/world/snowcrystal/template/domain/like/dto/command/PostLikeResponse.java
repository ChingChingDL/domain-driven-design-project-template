package world.snowcrystal.template.domain.like.dto.command;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;

@Builder
public class PostLikeResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -6657009935710516583L;
    private Long totalLikes;
}
