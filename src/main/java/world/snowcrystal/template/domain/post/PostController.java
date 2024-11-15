package world.snowcrystal.template.domain.post;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import world.snowcrystal.template.application.annotation.AuthorityCheck;
import world.snowcrystal.template.application.service.PostService;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.common.command.DeleteCommand;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.common.exception.ThrowUtils;
import world.snowcrystal.template.domain.login.command.LoginCommandService;
import world.snowcrystal.template.domain.post.dto.command.PostAddCommand;
import world.snowcrystal.template.domain.post.dto.command.PostEditCommand;
import world.snowcrystal.template.domain.post.dto.command.PostUpdateCommand;
import world.snowcrystal.template.domain.post.dto.query.PostQuery;
import world.snowcrystal.template.domain.post.dto.vo.PostVO;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.infrastructure.repository.po.Post;

import java.util.List;

/**
 * 文章接口
 */
//@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    @jakarta.annotation.Resource
    private PostService postService;

    @Resource
    private LoginCommandService loginCommandService;

    // region 增删改查

    /**
     * 创建
     *
     */
    @PostMapping("/add")
    public ApplicationResponse<Long> addPost(@RequestBody PostAddCommand postAddCommand, HttpServletRequest request) {
        if (postAddCommand == null) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postAddCommand, post);
        List<String> tags = postAddCommand.getTags();
        if (tags != null) {
            post.setTags(JSONUtil.toJsonStr(tags));
        }
        postService.validPost(post, true);
        User loginUser = loginCommandService.getLoginUserEntity(request);
        post.setUserId(loginUser.getId().getValue());
        post.setFavourNum(0);
        post.setThumbNum(0);
        boolean result = postService.save(post);
        ThrowUtils.throwIf(!result, ApplicationResponseStatusCode.OPERATION_ERROR);
        long newPostId = post.getId();
        return ApplicationResponse.success(newPostId);
    }

    /**
     * 删除
     *
     * @param deleteCommand
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public ApplicationResponse<Boolean> deletePost(@RequestBody DeleteCommand deleteCommand, HttpServletRequest request) {
        if (deleteCommand == null || deleteCommand.getId() <= 0) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
        }
        User user = loginCommandService.getLoginUserEntity(request);
        long id = deleteCommand.getId();
        // 判断是否存在
        Post oldPost = postService.getById(id);
        ThrowUtils.throwIf(oldPost == null, ApplicationResponseStatusCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldPost.getUserId().equals(user.getId().getValue()) && !user.checkIsAdmin()) {
            throw new BusinessException(ApplicationResponseStatusCode.NO_AUTH_ERROR);
        }
        boolean b = postService.removeById(id);
        return ApplicationResponse.success(b);
    }

    /**
     * 更新（仅管理员）
     */
    @PostMapping("/update")
    @AuthorityCheck
    public ApplicationResponse<Boolean> updatePost(@Nonnull @RequestBody PostUpdateCommand postUpdateCommand) {
        Post post = new Post();
        BeanUtils.copyProperties(postUpdateCommand, post);
        List<String> tags = postUpdateCommand.getTags();
        if (tags != null) {
            post.setTags(JSONUtil.toJsonStr(tags));
        }
        // 参数校验
        postService.validPost(post, false);
        long id = postUpdateCommand.getId();
        // 判断是否存在
        Post oldPost = postService.getById(id);
        ThrowUtils.throwIf(oldPost == null, ApplicationResponseStatusCode.NOT_FOUND_ERROR);
        boolean result = postService.updateById(post);
        return ApplicationResponse.success(result);
    }

    /**
     * 根据 id 获取
     */
    @GetMapping("/get/vo")
    public ApplicationResponse<PostVO> getPostVOById(@Min(0) @Nonnull Long id, HttpServletRequest request) {
        Post post = postService.getById(id);
        if (post == null) {
            throw new BusinessException(ApplicationResponseStatusCode.NOT_FOUND_ERROR);
        }
        return ApplicationResponse.success(postService.getPostVO(post, request));
    }

    /**
     * 分页获取列表（仅管理员）
     */
    @PostMapping("/list/page")
    @AuthorityCheck
    public ApplicationResponse<Page<Post>> listPostByPage(@RequestBody PostQuery postQueryRequest) {
        long current = postQueryRequest.getCurrent();
        long size = postQueryRequest.getSize();
        Page<Post> postPage = postService.page(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest));
        return ApplicationResponse.success(postPage);
    }

    /**
     * 分页获取列表（封装类）
     */
    @PostMapping("/list/page/vo")
    public ApplicationResponse<Page<PostVO>> listPostVOByPage(@RequestBody PostQuery postQueryRequest,
                                                              HttpServletRequest request) {
        long current = postQueryRequest.getCurrent();
        long size = postQueryRequest.getSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ApplicationResponseStatusCode.PARAMS_ERROR);
        Page<Post> postPage = postService.page(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest));
        return ApplicationResponse.success(postService.getPostVOPage(postPage, request));
    }

    /**
     * 分页获取当前用户创建的资源列表
     *
     */
    @PostMapping("/my/list/page/vo")
    public ApplicationResponse<Page<PostVO>> listMyPostVOByPage(@RequestBody PostQuery postQueryRequest,
                                                                HttpServletRequest request) {
        if (postQueryRequest == null) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
        }
        User loginUser = loginCommandService.getLoginUserEntity(request);
        postQueryRequest.setUserId(loginUser.getId().getValue());
        long current = postQueryRequest.getCurrent();
        long size = postQueryRequest.getSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ApplicationResponseStatusCode.PARAMS_ERROR);
        Page<Post> postPage = postService.page(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest));
        return ApplicationResponse.success(postService.getPostVOPage(postPage, request));
    }

    // endregion

    /**
     * 分页搜索（从 ES 查询，封装类）
     */
    @PostMapping("/search/page/vo")
    public ApplicationResponse<Page<PostVO>> searchPostVOByPage(@RequestBody PostQuery postQueryRequest,
                                                                HttpServletRequest request) {
        long size = postQueryRequest.getSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ApplicationResponseStatusCode.PARAMS_ERROR);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        return ApplicationResponse.success(postService.getPostVOPage(postPage, request));
    }

    /**
     * 编辑（用户）
     */
    @PostMapping("/edit")
    public ApplicationResponse<Boolean> editPost(@RequestBody PostEditCommand postEditCommand, HttpServletRequest request) {
        if (postEditCommand == null || postEditCommand.getId() <= 0) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postEditCommand, post);
        List<String> tags = postEditCommand.getTags();
        if (tags != null) {
            post.setTags(JSONUtil.toJsonStr(tags));
        }
        // 参数校验
        postService.validPost(post, false);
        User loginUser = loginCommandService.getLoginUserEntity(request);
        long id = postEditCommand.getId();
        // 判断是否存在
        Post oldPost = postService.getById(id);
        ThrowUtils.throwIf(oldPost == null, ApplicationResponseStatusCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        if (!oldPost.getUserId().equals(loginUser.getId().getValue()) && !loginUser.checkIsAdmin()) {
            throw new BusinessException(ApplicationResponseStatusCode.NO_AUTH_ERROR);
        }
        boolean result = postService.updateById(post);
        return ApplicationResponse.success(result);
    }

}
