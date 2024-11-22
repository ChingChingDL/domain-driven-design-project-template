package world.snowcrystal.template.application.controller;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.favorite.dto.command.FavouriteCommandService;
import world.snowcrystal.template.domain.favorite.dto.command.PostFavourAddCommand;
import world.snowcrystal.template.domain.favorite.dto.query.FavouriteQueryService;
import world.snowcrystal.template.domain.favorite.dto.query.UserFavouriteQuery;
import world.snowcrystal.template.domain.post.assembler.PostAssembler;
import world.snowcrystal.template.domain.post.dto.query.PostQueryResponse;
import world.snowcrystal.template.domain.post.entity.Post;

/**
 * 文章收藏接口
 */
@RestController
@RequestMapping("/post/favour")
@Slf4j
public class PostFavourController {

    @Resource
    private FavouriteCommandService favouriteCommandService;

    @Resource
    private FavouriteQueryService favouriteQueryService;
    @Resource
    private PostAssembler postAssembler;

    /**
     * 收藏某一篇文章
     *
     * @return resultNum 收藏变化数
     */
    @PostMapping("/")
    public ApplicationResponse<Long> doPostFavour(
            @Nonnull @RequestBody PostFavourAddCommand postFavourAddCommand,
            HttpServletRequest request) {
        Long l = favouriteCommandService.doPostFavour(postFavourAddCommand, request);
        return ApplicationResponse.success(l);
        // region old
//        User loginUser = loginCommandService.getLoginUserEntity(request);
        // 登录才能操作
//        final User loginUser = loginCommandService.getLoginUserEntity(request);
//        long postId = postFavourAddCommand.getPostId();
////        int result = postFavourService.doPostFavour(postId, loginUser);
//        return ApplicationResponse.success(0);
        // endregion old
    }

    /**
     * 获取用户的收藏的文章列表
     */
    @PostMapping("/list")
    public ApplicationResponse<Page<PostQueryResponse>> listMyFavourPostByPage(
            @RequestBody UserFavouriteQuery userFavouriteQuery) {

        Page<Post> postPage = favouriteQueryService.listOnesFavours(userFavouriteQuery);
        Page<PostQueryResponse> build = Page.<PostQueryResponse>builder().total(postPage.getTotal())
                .records(postPage.stream().map(postAssembler::toQueryResponse).toList())
                .current(postPage.getCurrent())
                .size(postPage.getSize())
                .build();
        return ApplicationResponse.success(build);
        // region old
        //        if (postQueryRequest == null) {
//            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
//        }
//        User loginUser = loginCommandService.getLoginUserEntity(request);
//        long current = postQueryRequest.getCurrent();
//        long size = postQueryRequest.getSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ApplicationResponseStatusCode.PARAMS_ERROR);
//        Page<PostPO> postPage = postFavourService.listFavourPostByPage(new Page<>(current, size),
//                postService.getQueryWrapper(postQueryRequest), loginUser.getId().getValue());
//        return ApplicationResponse.success(postService.getPostVOPage(postPage, request));
        // endregion old
    }

//    /**
//     * 获取用户收藏的文章列表
//     */
//    @PostMapping("/list/page")
//    public ApplicationResponse<Page<PostQueryResponse>> listFavourPostByPage(@RequestBody PostFavourQuery postFavourQueryRequest,
//                                                                             HttpServletRequest request) {
//        if (postFavourQueryRequest == null) {
//            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
//        }
//        long current = postFavourQueryRequest.getCurrent();
//        long size = postFavourQueryRequest.getSize();
//        Long userId = postFavourQueryRequest.getUserId();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20 || userId == null, ApplicationResponseStatusCode.PARAMS_ERROR);
//        Page<PostPO> postPage = postFavourService.listFavourPostByPage(new Page<>(current, size),
//                postService.getQueryWrapper(postFavourQueryRequest.getPostQueryRequest()), userId);
//        return ApplicationResponse.success(postService.getPostVOPage(postPage, request));
//    }
}
