package world.snowcrystal.template.application.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import world.snowcrystal.template.domain.post.dto.query.PostQuery;
import world.snowcrystal.template.infrastructure.repository.po.Post;
import world.snowcrystal.template.domain.post.dto.vo.PostVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 文章服务
 *
 */
public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     */
    void validPost(Post post, boolean add);

    /**
     * 获取查询条件
     *
     */
    QueryWrapper<Post> getQueryWrapper(PostQuery postQueryRequest);

    /**
     * 从 ES 查询
     *
     */
    Page<Post> searchFromEs(PostQuery postQueryRequest);

    /**
     * 获取文章封装
     *
     */
    PostVO getPostVO(Post post, HttpServletRequest request);

    /**
     * 分页获取文章封装
     *
     */
    Page<PostVO> getPostVOPage(Page<Post> postPage, HttpServletRequest request);
}
