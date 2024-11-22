package world.snowcrystal.template.domain.favorite;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.post.PostService;
import world.snowcrystal.template.infrastructure.repository.mapper.PostThumbMapper;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;
import world.snowcrystal.template.infrastructure.repository.po.PostThumbPO;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;
import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文章点赞服务实现
 *
 *
 *
 */
//@Service
public class PostThumbServiceImpl extends ServiceImpl<PostThumbMapper, PostThumbPO>
          {

    @Resource
    private PostService postService;

    /**
     * 点赞
     */

    public int doPostThumb(long postId, UserPO loginUser) {
        // 判断实体是否存在，根据类别获取实体
        PostPO postPO = postService.getById(postId);
        if (postPO == null) {
            throw new BusinessException(ApplicationResponseStatusCode.NOT_FOUND_ERROR);
        }
        // 是否已点赞
        long userId = loginUser.getId();
        // 每个用户串行点赞
        // 锁必须要包裹住事务方法
        PostThumbService postThumbService = (PostThumbService) AopContext.currentProxy();
        synchronized (String.valueOf(userId).intern()) {
            return postThumbService.doPostThumbInner(userId, postId);
        }
    }

    /**
     * 封装了事务的方法
     *
     * @param userId
     * @param postId
     * @return
     */

    @Transactional(rollbackFor = Exception.class)
    public int doPostThumbInner(long userId, long postId) {
        PostThumbPO postThumbPO = new PostThumbPO();
        postThumbPO.setUserId(userId);
        postThumbPO.setPostId(postId);
        QueryWrapper<PostThumbPO> thumbQueryWrapper = new QueryWrapper<>(postThumbPO);
        PostThumbPO oldPostThumbPO = this.getOne(thumbQueryWrapper);
        boolean result;
        // 已点赞
        if (oldPostThumbPO != null) {
            result = this.remove(thumbQueryWrapper);
            if (result) {
                // 点赞数 - 1
                result = postService.update()
                        .eq("id", postId)
                        .gt("thumbNum", 0)
                        .setSql("thumbNum = thumbNum - 1")
                        .update();
                return result ? -1 : 0;
            } else {
                throw new BusinessException(ApplicationResponseStatusCode.SYSTEM_ERROR);
            }
        } else {
            // 未点赞
            result = this.save(postThumbPO);
            if (result) {
                // 点赞数 + 1
                result = postService.update()
                        .eq("id", postId)
                        .setSql("thumbNum = thumbNum + 1")
                        .update();
                return result ? 1 : 0;
            } else {
                throw new BusinessException(ApplicationResponseStatusCode.SYSTEM_ERROR);
            }
        }
    }

}




