package world.snowcrystal.template.domain.favorite.dto.query;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;
import world.snowcrystal.template.domain.common.query.PageQuery;

/**
 * 用户收藏查询
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFavouriteQuery extends PageQuery {
    @Nonnull
    @Min(0)
    private Long userId;
}
