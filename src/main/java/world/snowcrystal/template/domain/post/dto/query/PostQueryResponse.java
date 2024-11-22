package world.snowcrystal.template.domain.post.dto.query;

import lombok.Data;
import world.snowcrystal.template.domain.user.dto.query.UserQueryResponse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章视图
 */
@Data
public class PostQueryResponse implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 收藏数
     */
    private Integer favours;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 创建人信息
     */
    private UserQueryResponse user;

}
