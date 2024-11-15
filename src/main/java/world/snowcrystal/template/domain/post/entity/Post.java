package world.snowcrystal.template.domain.post.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import world.snowcrystal.template.domain.common.type.Id;
import world.snowcrystal.template.domain.post.type.Title;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 */
@Data
public class Post implements Serializable {

    /**
     * id
     */
    private Id id;

    /**
     * 标题
     */
    private Title title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表 json
     */
    private String tags;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Id userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer deleted;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}
