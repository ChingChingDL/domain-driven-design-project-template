package world.snowcrystal.template.application.controller;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.snowcrystal.template.domain.login.annotation.AuthorityCheck;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.management.dto.command.AdminUserUpdateCommand;
import world.snowcrystal.template.domain.post.assembler.PostAssembler;
import world.snowcrystal.template.domain.post.dto.command.PostCommandService;
import world.snowcrystal.template.domain.post.dto.command.PostEditCommand;
import world.snowcrystal.template.domain.post.dto.command.PostEditResponse;
import world.snowcrystal.template.domain.post.dto.query.PostQuery;
import world.snowcrystal.template.domain.post.dto.query.PostQueryResponse;
import world.snowcrystal.template.domain.post.dto.query.PostQueryService;
import world.snowcrystal.template.domain.post.entity.Post;
import world.snowcrystal.template.domain.user.dto.command.UserCommandService;
import world.snowcrystal.template.domain.user.dto.command.UserCreateCommand;
import world.snowcrystal.template.domain.user.dto.query.UserQuery;
import world.snowcrystal.template.domain.user.dto.query.UserQueryService;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.exception.AccountTakenException;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;

@RestController
@RequestMapping("/management")
public class ManagementController {

    @Resource
    private UserCommandService userCommandService;

    @Resource
    private UserQueryService userQueryService;

    @Resource
    private PostCommandService postCommandService;

    @Resource
    private PostAssembler postAssembler;
    @Resource
    private PostQueryService postQueryService;

    /**
     * 创建用户
     */
    @PostMapping("/user")
    @AuthorityCheck
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse<Long> createUser(@Nonnull @RequestBody
                                                UserCreateCommand userCreateCommand) {
        return ApplicationResponse.success(userCommandService.createUser(userCreateCommand).getValue());
    }

    @ExceptionHandler(AccountTakenException.class)
    public ResponseEntity<ApplicationResponse<Void>> handlePostConflict(AccountTakenException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApplicationResponse.noOperation(e.getMessage()));
    }

    /**
     * 根据 id 获取用户（仅管理员）
     */
    @GetMapping("/user/{userId}")
    @AuthorityCheck
    public ApplicationResponse<User> getUserById(@PathVariable("userId") @Nonnull @Min(0) Long id) {
        return ApplicationResponse.success(userQueryService.getById(new Id(id)));
    }

    /**
     * 分页获取用户列表（仅管理员）
     */
    @PostMapping("/user/list")
    @AuthorityCheck
    public ApplicationResponse<Page<UserPO>> listUserByPage(@RequestBody UserQuery userQueryRequest) {
        return ApplicationResponse.success(userQueryService.page(userQueryRequest));
    }


    /**
     * 删除用户
     */
    @DeleteMapping("/user/{userId}")
    @AuthorityCheck
    public ApplicationResponse<Void> deleteUser(@PathVariable("userId") @Nonnull Long userId) {
        userCommandService.deleteUser(new Id(userId));
        return ApplicationResponse.success();
    }

    /**
     * 更新用户
     */
    @PutMapping("/user")
    @AuthorityCheck
    public ApplicationResponse<Void> updateUser(
            @Nonnull @RequestBody AdminUserUpdateCommand adminUserUpdateCommand) {
        userCommandService.update(adminUserUpdateCommand);
        return ApplicationResponse.success();
    }


    /**
     * 分页获取列表（仅管理员）
     */
    @PostMapping("/list/page")
    @AuthorityCheck
    public ApplicationResponse<Page<PostQueryResponse>> listPostByPage(@RequestBody
                                                                       PostQuery postQueryRequest) {
        Page<PostQueryResponse> page = postQueryService.page(postQueryRequest);
        return ApplicationResponse.success(page);
//        long current = postQueryRequest.getCurrent();
//        long size = postQueryRequest.getSize();
////        Page<PostPO> postPage = postService.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(current, size),
////                postService.getQueryWrapper(postQueryRequest));
//        return ApplicationResponse.success(postQueryService.page(postQueryRequest));
    }

    /**
     * 更新（仅管理员）
     */
    @PostMapping("/update")
    @AuthorityCheck
    public ApplicationResponse<PostEditResponse> updatePost(@Nonnull
                                                            @RequestBody PostEditCommand postEditCommand, HttpServletRequest request) {
        Post post = postCommandService.editPost(postEditCommand,request);
        return ApplicationResponse.success(postAssembler.toEditResponse(post));

        // region old
//        PostPO postPO = new PostPO();
//        BeanUtils.copyProperties(postUpdateCommand, postPO);
//        List<String> tags = postUpdateCommand.getTags();
//        if (tags != null) {
//            postPO.setTags(JSONUtil.toJsonStr(tags));
//        }
//        // 参数校验
//        postService.validPost(postPO, false);
//        long id = postUpdateCommand.getId();
//        // 判断是否存在
//        PostPO oldPostPO = postService.getById(id);
//        ThrowUtils.throwIf(oldPostPO == null, ApplicationResponseStatusCode.NOT_FOUND_ERROR);
//        boolean result = postService.updateById(postPO);
//        return ApplicationResponse.success(result);
        //endregion old
    }

}
