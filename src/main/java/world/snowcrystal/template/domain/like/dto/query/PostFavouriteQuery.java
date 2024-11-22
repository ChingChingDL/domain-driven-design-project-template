package world.snowcrystal.template.domain.like.dto.query;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import lombok.Data;
import world.snowcrystal.template.domain.common.query.Query;

/**
 * 文章收藏数量查询
 */
@Data
public class PostFavouriteQuery implements Query {

    @Nonnull
    @Min(0)
    private Long postId;

}
