package world.snowcrystal.template.domain.favorite;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Transactional;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.post.PostService;
import world.snowcrystal.template.infrastructure.repository.mapper.PostFavourMapper;
import world.snowcrystal.template.infrastructure.repository.po.PostFavourPO;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;

/**
 * 文章收藏服务实现
 */
//@Service
public class PostFavourService extends ServiceImpl<PostFavourMapper, PostFavourPO> {

    @Resource
    private PostService postService;

    /**
     * 文章收藏
     */
    public int doPostFavour(long postId, UserPO loginUser) {
        // 判断是否存在
        PostPO postPO = postService.getById(postId);
        if (postPO == null) {
            throw new BusinessException(ApplicationResponseStatusCode.NOT_FOUND_ERROR);
        }
        // 是否已文章收藏
        long userId = loginUser.getId();
        // 每个用户串行文章收藏
        // 锁必须要包裹住事务方法
        PostFavourService postFavourService = (PostFavourService) AopContext.currentProxy();
        synchronized (String.valueOf(userId).intern()) {
            return postFavourService.doPostFavourInner(userId, postId);
        }
    }


    public Page<PostPO> listFavourPostByPage(IPage<PostPO> page, Wrapper<PostPO> queryWrapper, long favourUserId) {
        if (favourUserId <= 0) {
            return new Page<>();
        }
        return baseMapper.listFavourPostByPage(page, queryWrapper, favourUserId);
    }

    /**
     * 封装了事务的方法
     *
     * @param userId
     * @param postId
     * @return
     */

    @Transactional(rollbackFor = Exception.class)
    public int doPostFavourInner(long userId, long postId) {
        PostFavourPO postFavourPO = new PostFavourPO();
        postFavourPO.setUserId(userId);
        postFavourPO.setPostId(postId);
        QueryWrapper<PostFavourPO> postFavourQueryWrapper = new QueryWrapper<>(postFavourPO);
        PostFavourPO oldPostFavourPO = this.getOne(postFavourQueryWrapper);
        boolean result;
        // 已收藏
        if (oldPostFavourPO != null) {
            result = this.remove(postFavourQueryWrapper);
            if (result) {
                // 文章收藏数 - 1
                result = postService.update()
                        .eq("id", postId)
                        .gt("favourNum", 0)
                        .setSql("favourNum = favourNum - 1")
                        .update();
                return result ? -1 : 0;
            } else {
                throw new BusinessException(ApplicationResponseStatusCode.SYSTEM_ERROR);
            }
        } else {
            // 未文章收藏
            result = this.save(postFavourPO);
            if (result) {
                // 文章收藏数 + 1
                result = postService.update()
                        .eq("id", postId)
                        .setSql("favourNum = favourNum + 1")
                        .update();
                return result ? 1 : 0;
            } else {
                throw new BusinessException(ApplicationResponseStatusCode.SYSTEM_ERROR);
            }
        }
    }

}




