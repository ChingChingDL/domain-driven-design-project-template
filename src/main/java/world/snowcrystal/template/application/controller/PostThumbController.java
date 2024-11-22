package world.snowcrystal.template.application.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.like.dto.command.*;
import world.snowcrystal.template.domain.like.dto.query.PostLikeQuery;
import world.snowcrystal.template.domain.like.dto.query.PostLikeQueryService;

/**
 * 文章点赞接口
 */
@Validated
@RestController
@RequestMapping("/post/like")
@Slf4j
public class PostThumbController {

    @Resource
    private PostLikeCommandService postLikeCommandService;

    @Resource
    private PostLikeQueryService postLikeQueryService;

    /**
     * 点赞 / 取消点赞
     */
    @PostMapping("/")
    public ApplicationResponse<PostLikeResponse> doThumb(@RequestBody
                                                         PostLikeAddCommand postLikeAddCommand,
                                                         HttpServletRequest request) {
        return ApplicationResponse.success(postLikeCommandService.doPostLike(postLikeAddCommand, request));

//        if (postLikeAddCommand == null || postLikeAddCommand.getPostId() <= 0) {
//            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR);
//        }
//        // 登录才能点赞
//        final User loginUser = loginCommandService.getLoginUserEntity(request);
//        long postId = postLikeAddCommand.getPostId();
////        int result = postThumbService.doPostThumb(postId, loginUser);
//        return ApplicationResponse.success(0);
    }

    @DeleteMapping("/")
    public ApplicationResponse<PostLikeCancelResponse> cancelThumb(@RequestBody
                                                                   PostLikeCancelCommand postLikeCancelCommand,
                                                                   HttpServletRequest request) {
        return ApplicationResponse.success(postLikeCommandService.cancelLike(postLikeCancelCommand, request));
    }


    // 点赞数量
    @GetMapping("/{postId}")
    public ApplicationResponse<Long> countTotalLikes(@PathVariable("postId") Long postId) {
        Long l = postLikeQueryService.count(new PostLikeQuery(postId));
        return ApplicationResponse.success(l);
    }


}
