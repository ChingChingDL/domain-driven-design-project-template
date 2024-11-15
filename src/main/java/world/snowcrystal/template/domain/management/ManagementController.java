package world.snowcrystal.template.domain.management;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import world.snowcrystal.template.application.annotation.AuthorityCheck;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.common.type.Id;
import world.snowcrystal.template.domain.management.dto.command.AdminUserUpdateCommand;
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
    @DeleteMapping("/{userId}")
    @AuthorityCheck
    public ApplicationResponse<Void> deleteUser(@PathVariable("userId") @Nonnull Long userId) {
        userCommandService.deleteUser(new Id(userId));
        return ApplicationResponse.success();
    }

    /**
     * 更新用户
     */
    @PutMapping("/")
    @AuthorityCheck
    public ApplicationResponse<Void> updateUser(
            @Nonnull @RequestBody AdminUserUpdateCommand adminUserUpdateCommand) {
        userCommandService.update(adminUserUpdateCommand);
        return ApplicationResponse.success();
    }


}
