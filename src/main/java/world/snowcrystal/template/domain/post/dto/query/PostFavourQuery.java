package world.snowcrystal.template.domain.post.dto.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import world.snowcrystal.template.domain.common.query.PageQuery;

import java.io.Serial;

/**
 * 文章收藏查询请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostFavourQuery extends PageQuery {

    /**
     * 文章查询请求
     */
    private PostQuery postQueryRequest;

    /**
     * 用户 id
     */
    private Long userId;
    @Serial
    private static final long serialVersionUID = 1L;
}
