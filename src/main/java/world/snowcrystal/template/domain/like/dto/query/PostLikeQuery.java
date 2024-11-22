package world.snowcrystal.template.domain.like.dto.query;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import lombok.Data;
import world.snowcrystal.template.domain.common.query.Query;

/**
 * 文章点赞数量查询
 */
@Data
public class PostLikeQuery implements Query {
    @Nonnull
    @Min(0)
    private Long postId;
}
