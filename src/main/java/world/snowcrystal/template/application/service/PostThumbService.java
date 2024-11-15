package world.snowcrystal.template.application.service;

import world.snowcrystal.template.infrastructure.repository.po.PostThumb;
import com.baomidou.mybatisplus.extension.service.IService;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;

/**
 * 文章点赞服务
 *
 *
 *
 */
public interface PostThumbService extends IService<PostThumb> {

    /**
     * 点赞
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doPostThumb(long postId, UserPO loginUser);

    /**
     * 文章点赞（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostThumbInner(long userId, long postId);
}
