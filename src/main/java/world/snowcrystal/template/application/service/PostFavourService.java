package world.snowcrystal.template.application.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import world.snowcrystal.template.infrastructure.repository.po.Post;
import world.snowcrystal.template.infrastructure.repository.po.PostFavour;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;

/**
 * 文章收藏服务
 *
 *
 *
 */
public interface PostFavourService extends IService<PostFavour> {

    /**
     * 文章收藏
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doPostFavour(long postId, UserPO loginUser);

    /**
     * 分页获取用户收藏的文章列表
     *
     * @param page
     * @param queryWrapper
     * @param favourUserId
     * @return
     */
    Page<Post> listFavourPostByPage(IPage<Post> page, Wrapper<Post> queryWrapper,
            long favourUserId);

    /**
     * 文章收藏（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostFavourInner(long userId, long postId);
}
