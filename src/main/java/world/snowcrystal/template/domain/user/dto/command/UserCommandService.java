package world.snowcrystal.template.domain.user.dto.command;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import world.snowcrystal.template.domain.user.assembler.UserAssembler;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.enums.SessionAttributeEnum;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.management.dto.command.AdminUserUpdateCommand;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.entity.UserFactory;
import world.snowcrystal.template.domain.user.repository.UserRepository;
import world.snowcrystal.template.domain.user.service.UserDomainService;
import world.snowcrystal.template.domain.user.primitive.Username;

/**
 * 用户服务实现
 */
@Service
@Slf4j
public class UserCommandService {

    @Resource
    private UserFactory userFactory;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserDomainService userDomainService;


    @Resource
    private UserAssembler userAssembler;

    public Id createUser(UserCreateCommand userCreateCommand) {
        User user = userFactory.create(userCreateCommand);
        userRepository.save(user);
        return user.getId();
    }


    @Transactional
    public void deleteUser(Id userId) {
        userRepository.remove(userId);
    }


    public void update(AdminUserUpdateCommand adminUserUpdateCommand) {
        // TODO Rate limit
        User user = userRepository.load(Id.of(adminUserUpdateCommand.getId()));
        userDomainService.updateUser(user, adminUserUpdateCommand);
        userRepository.save(user);
    }


    public void update(UserUpdateCommand userUpdateCommand) {
        User user = userRepository.load(Id.of(userUpdateCommand.getId()));
        user.changeAvatar(userUpdateCommand.getAvatar())
                .changeProfile(userUpdateCommand.getProfile());
        userRepository.save(user);
    }


    public void updateLoginUser(UserUpdateCommand userUpdateCommand, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionAttributeEnum.LOGIN_USER.getValue());
        if (user == null) {
            throw new BusinessException(ApplicationResponseStatusCode.NOT_LOGIN_ERROR);
        }
        userDomainService.changeUsername(user, Username.of(userUpdateCommand.getUsername()));
        user.changeAvatar(userUpdateCommand.getAvatar())
                .changeProfile(userUpdateCommand.getProfile());
        userRepository.save(user);
    }

}
