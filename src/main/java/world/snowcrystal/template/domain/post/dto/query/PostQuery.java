package world.snowcrystal.template.domain.post.dto.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import world.snowcrystal.template.domain.common.query.PageQuery;

import java.io.Serial;
import java.util.List;

/**
 * 查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostQuery extends PageQuery {

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long notId;

    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 至少有一个标签
     */
    private List<String> orTags;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 收藏用户 id
     */
    private Long favourUserId;
    @Serial
    private static final long serialVersionUID = 1L;
}
