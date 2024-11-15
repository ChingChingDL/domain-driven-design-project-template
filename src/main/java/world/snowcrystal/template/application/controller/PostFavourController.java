package world.snowcrystal.template.application.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import world.snowcrystal.template.application.service.PostFavourService;
import world.snowcrystal.template.application.service.PostService;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.common.exception.ThrowUtils;
import world.snowcrystal.template.domain.login.command.LoginCommandService;
import world.snowcrystal.template.domain.post.dto.command.PostFavourAddCommand;
import world.snowcrystal.template.domain.post.dto.query.PostFavourQuery;
import world.snowcrystal.template.domain.post.dto.query.PostQuery;
import world.snowcrystal.template.domain.post.dto.vo.PostVO;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.infrastructure.repository.po.Post;

/**
 * 文章收藏接口
 */
//@RestController
@RequestMapping("/post_favour")
@Slf4j
public class PostFavourController {

    @Resource
    private PostFavourService postFavourService;

    @Resource
    private PostService postService;

    @Resource
    private LoginCommandService loginCommandService;

    /**
     * 收藏 / 取消收藏
     *
     * @return resultNum 收藏变化数
     */
    @PostMapping("/")
    public ApplicationResponse<Integer> doPostFavour(
            @Nonnull @RequestBody PostFavourAddCommand postFavourAddCommand,
                                                     HttpServletRequest request) {
        // 登录才能操作
        final User loginUser = loginCommandService.getLoginUserEntity(request);
        long postId = postFavourAddCommand.getPostId();
//        int result = postFavourService.doPostFavour(postId, loginUser);
        return ApplicationResponse.success(0);
    }

    /**
     * 获取我收藏的文章列表
     *
     */
    @PostMapping("/my/list/page")
    public ApplicationResponse<Page<PostVO>> listMyFavourPostByPage(@RequestBody PostQuery postQueryRequest,
                                                                    HttpServletRequest request) {
        if (postQueryRequest == null) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
        }
        User loginUser = loginCommandService.getLoginUserEntity(request);
        long current = postQueryRequest.getCurrent();
        long size = postQueryRequest.getSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ApplicationResponseStatusCode.PARAMS_ERROR);
        Page<Post> postPage = postFavourService.listFavourPostByPage(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest), loginUser.getId().getValue());
        return ApplicationResponse.success(postService.getPostVOPage(postPage, request));
    }

    /**
     * 获取用户收藏的文章列表
     */
    @PostMapping("/list/page")
    public ApplicationResponse<Page<PostVO>> listFavourPostByPage(@RequestBody PostFavourQuery postFavourQueryRequest,
                                                                  HttpServletRequest request) {
        if (postFavourQueryRequest == null) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
        }
        long current = postFavourQueryRequest.getCurrent();
        long size = postFavourQueryRequest.getSize();
        Long userId = postFavourQueryRequest.getUserId();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20 || userId == null, ApplicationResponseStatusCode.PARAMS_ERROR);
        Page<Post> postPage = postFavourService.listFavourPostByPage(new Page<>(current, size),
                postService.getQueryWrapper(postFavourQueryRequest.getPostQueryRequest()), userId);
        return ApplicationResponse.success(postService.getPostVOPage(postPage, request));
    }
}
