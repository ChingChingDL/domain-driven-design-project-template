package world.snowcrystal.template.application.controller;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.user.dto.command.UserCommandService;
import world.snowcrystal.template.domain.user.dto.command.UserUpdateCommand;
import world.snowcrystal.template.domain.user.dto.query.UserQuery;
import world.snowcrystal.template.domain.user.dto.query.UserQueryResponse;
import world.snowcrystal.template.domain.user.dto.query.UserQueryService;

/**
 * 用户接口
 */
@Validated
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserCommandService userCommandService;
    @Resource
    private UserQueryService userQueryService;

    // region 增删改查


    /**
     * 根据 id 获取包装类
     */
    @GetMapping("/{userId}")
    public ApplicationResponse<UserQueryResponse> retrieveUser(@PathVariable("userId") @Nonnull @Min(0) Long userId) {
        return ApplicationResponse.success(userQueryService.getUserVO(new Id(userId)));
    }

    /**
     * 分页获取用户封装列表
     */
    @PostMapping("/list")
    public ApplicationResponse<Page<UserQueryResponse>> listUsers(@Nonnull
                                                              @RequestBody
                                                              UserQuery userQueryRequest) {
        return ApplicationResponse.success(userQueryService.pageVO(userQueryRequest));
    }

    // endregion

    /**
     * 更新个人信息
     */
    @PutMapping("/me")
    public ApplicationResponse<Void> updateMyInformation(@Nonnull @RequestBody UserUpdateCommand userUpdateCommand,
                                                  HttpServletRequest request) {
        userCommandService.updateLoginUser(userUpdateCommand, request);
        return ApplicationResponse.success();
    }
}
