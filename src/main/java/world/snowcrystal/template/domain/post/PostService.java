package world.snowcrystal.template.domain.post;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import world.snowcrystal.template.domain.post.dto.query.PostQuery;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;
import world.snowcrystal.template.domain.post.dto.query.PostQueryResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 文章服务
 *
 */
public interface PostService extends IService<PostPO> {

    /**
     * 校验
     *
     */
    void validPost(PostPO postPO, boolean add);

    /**
     * 获取查询条件
     *
     */
    QueryWrapper<PostPO> getQueryWrapper(PostQuery postQueryRequest);

    /**
     * 从 ES 查询
     *
     */
    Page<PostPO> searchFromEs(PostQuery postQueryRequest);

    /**
     * 获取文章封装
     *
     */
    PostQueryResponse getPostVO(PostPO postPO, HttpServletRequest request);

    /**
     * 分页获取文章封装
     *
     */
    Page<PostQueryResponse> getPostVOPage(Page<PostPO> postPage, HttpServletRequest request);
}
