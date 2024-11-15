package world.snowcrystal.template.domain.user.dto.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import world.snowcrystal.template.domain.common.query.PageQuery;

import java.io.Serial;

/**
 * 用户查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends PageQuery {
    /**
     * id
     */
    private Long id;

    /**
     * 开放平台id
     */
    private String unionId;

    /**
     * 公众号openId
     */
    private String mpOpenId;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 简介
     */
    private String profile;

    /**
     * 用户角色：user/admin/ban
     */
    private String role;
    @Serial
    private static final long serialVersionUID = 1L;
}
