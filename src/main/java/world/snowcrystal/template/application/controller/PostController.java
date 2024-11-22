package world.snowcrystal.template.application.controller;


import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.common.command.DeleteCommand;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.login.command.LoginCommandService;
import world.snowcrystal.template.domain.post.PostService;
import world.snowcrystal.template.domain.post.assembler.PostAssembler;
import world.snowcrystal.template.domain.post.dto.command.PostAddCommand;
import world.snowcrystal.template.domain.post.dto.command.PostCommandService;
import world.snowcrystal.template.domain.post.dto.command.PostEditCommand;
import world.snowcrystal.template.domain.post.dto.command.PostEditResponse;
import world.snowcrystal.template.domain.post.dto.query.PostQuery;
import world.snowcrystal.template.domain.post.dto.query.PostQueryResponse;
import world.snowcrystal.template.domain.post.dto.query.PostQueryService;
import world.snowcrystal.template.domain.post.entity.Post;

/**
 * 文章接口
 */
@RestController
@RequestMapping("/post")
@Slf4j
@Validated
public class PostController {

    @Resource
    private PostService postService;

    @Resource
    private LoginCommandService loginCommandService;

    @Resource
    private PostCommandService postCommandService;

    @Resource
    private PostQueryService postQueryService;

    @Resource
    private PostAssembler postAssembler;

    // region 增删改查

    /**
     * 创建
     */
    @PostMapping("/")
    public ApplicationResponse<Long> addPost(@Nonnull @RequestBody
                                             PostAddCommand postAddCommand, HttpServletRequest request) {
        Id id = postCommandService.addPost(postAddCommand, request);
        return ApplicationResponse.success(id.getValue());
        // region old
//        PostPO postPO = new PostPO();
//        BeanUtils.copyProperties(postAddCommand, postPO);
//        List<String> tags = postAddCommand.getTags();
//        if (tags != null) {
//            postPO.setTags(JSONUtil.toJsonStr(tags));
//        }
//        postService.validPost(postPO, true);
//        User loginUser = loginCommandService.getLoginUserEntity(request);
//        postPO.setUserId(loginUser.getId().getValue());
//        postPO.setFavourNum(0);
//        postPO.setThumbNum(0);
//        boolean result = postService.save(postPO);
//        ThrowUtils.throwIf(!result, ApplicationResponseStatusCode.OPERATION_ERROR);
//        long newPostId = postPO.getId();
//        return ApplicationResponse.success(newPostId);
        // endregion old
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public ApplicationResponse<Boolean> deletePost(@Nonnull @PathVariable
                                                   @Min(0) Long id,
                                                   HttpServletRequest request) {
        postCommandService.deletePost(new DeleteCommand(id), request);
        return ApplicationResponse.success(true);
        // region old
//        User user = loginCommandService.getLoginUserEntity(request);
//        long id = deleteCommand.getId();
//        // 判断是否存在
//        PostPO oldPostPO = postService.getById(id);
//        ThrowUtils.throwIf(oldPostPO == null, ApplicationResponseStatusCode.NOT_FOUND_ERROR);
//        // 仅本人或管理员可删除
//        if (!oldPostPO.getUserId().equals(user.getId().getValue()) && !user.checkIsAdmin()) {
//            throw new BusinessException(ApplicationResponseStatusCode.NO_AUTH_ERROR);
//        }
//        boolean b = postService.removeById(id);
//        return ApplicationResponse.success(b);
        //endregion old
    }


    /**
     * 根据 id 获取
     */
    @GetMapping("/{id}")
    public ApplicationResponse<PostQueryResponse> retrievePostById(@Min(0)
                                                                   @Nonnull @PathVariable Long id) {
        Post post = postQueryService.findById(id);
        return ApplicationResponse.success(postAssembler.toQueryResponse(post));
        // region old
//        long id = postQueryRequest.getId();
//        // 判断是否存在
//        PostPO postPO = postService.getById(id);
//        if (postPO == null) {
//            throw new BusinessException(ApplicationResponseStatusCode.NOT_FOUND_ERROR);
//        }
//        return ApplicationResponse.success(postService.getPostVO(postPO, request));
        //endregion old
    }


//    /**
//     * 分页获取列表（封装类）
//     */
//    @PostMapping("/list/page/vo")
//    public ApplicationResponse<Page<PostQueryResponse>> listPostVOByPage(@RequestBody PostQuery postQueryRequest,
//                                                                         HttpServletRequest request) {
//        long current = postQueryRequest.getCurrent();
//        long size = postQueryRequest.getSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ApplicationResponseStatusCode.PARAMS_ERROR);
//        Page<PostPO> postPage = postService.page(new Page<>(current, size),
//                postService.getQueryWrapper(postQueryRequest));
//        return ApplicationResponse.success(postService.getPostVOPage(postPage, request));
//    }

    /**
     * 分页获取当前用户创建的资源列表
     */
    @GetMapping("/mine/")
    public ApplicationResponse<Page<PostQueryResponse>> listMyPostVOByPage(@Nonnull @RequestBody PostQuery postQueryRequest,
                                                                           HttpServletRequest request) {
        return ApplicationResponse.success(postQueryService.mine(postQueryRequest, request));
    }

    // endregion

    /**
     * 分页搜索（从 ES 查询，封装类）
     */
    @PostMapping("/search/page/vo")
    public ApplicationResponse<Page<PostQueryResponse>> searchPostVOByPage(@RequestBody PostQuery postQueryRequest) {
        Page<PostQueryResponse> page = postQueryService.page(postQueryRequest);
        return ApplicationResponse.success(page);

        // region old
        // 限制爬虫
//        long size = postQueryRequest.getSize();
//        ThrowUtils.throwIf(size > 20, ApplicationResponseStatusCode.PARAMS_ERROR);
//        Page<PostPO> postPage = postService.searchFromEs(postQueryRequest);
//        return ApplicationResponse.success(postService.getPostVOPage(postPage, request));
        //endregion old
    }

    /**
     * 编辑（用户）
     */
    @PostMapping("/edit")
    public ApplicationResponse<PostEditResponse> editPost(@Nonnull @RequestBody PostEditCommand postEditCommand, HttpServletRequest request) {
        Post post = postCommandService.editPost(postEditCommand, request);
        return ApplicationResponse.success(postAssembler.toEditResponse(post));
//         region old
//        PostPO postPO = new PostPO();
//        BeanUtils.copyProperties(postEditCommand, postPO);
//        List<String> tags = postEditCommand.getTags();
//        if (tags != null) {
//            postPO.setTags(JSONUtil.toJsonStr(tags));
//        }
//        // 参数校验
//        postService.validPost(postPO, false);
//        User loginUser = loginCommandService.getLoginUserEntity(request);
//        long id = postEditCommand.getId();
//        // 判断是否存在
//        PostPO oldPostPO = postService.getById(id);
//        ThrowUtils.throwIf(oldPostPO == null, ApplicationResponseStatusCode.NOT_FOUND_ERROR);
//        // 仅本人或管理员可编辑
//        if (!oldPostPO.getUserId().equals(loginUser.getId().getValue()) && !loginUser.checkIsAdmin()) {
//            throw new BusinessException(ApplicationResponseStatusCode.NO_AUTH_ERROR);
//        }
//        boolean result = postService.updateById(postPO);
//        return ApplicationResponse.success(result);
        // endregion old
    }

}
