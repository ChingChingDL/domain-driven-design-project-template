package world.snowcrystal.template.domain.user.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.register.component.PasswordEncoder;
import world.snowcrystal.template.domain.register.component.UserPasswordGenerator;
import world.snowcrystal.template.domain.register.component.UsernameGenerator;
import world.snowcrystal.template.domain.management.dto.command.AdminUserUpdateCommand;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.repository.UserRepository;
import world.snowcrystal.template.domain.user.type.Account;
import world.snowcrystal.template.domain.user.type.Username;

@Service
public class UserDomainService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UsernameGenerator usernameGenerator;

    @Resource
    private UserPasswordGenerator passwordGenerator;

    @Resource
    private PasswordEncoder passwordEncoder;

    public void changeUsername(User user, Username newUsername) {
        // check replication
        if (user.getUsername().equals(newUsername)) {
            return;
        }
        if (userRepository.checkExists(newUsername)) {
            throw new BusinessException(ApplicationResponseStatusCode.OPERATION_ERROR, "Username already exists");
        }
        user.changeUsername(newUsername);
    }

    public boolean checkExists(Username username) {
        return userRepository.checkExists(username);
    }

    public boolean checkExists(Account account) {
        return userRepository.checkExists(account);
    }


    public void updateUser(User user, AdminUserUpdateCommand adminUserUpdateCommand) {
        changeUsername(user, Username.of(adminUserUpdateCommand.getUsername()));
        user.changeAvatar(adminUserUpdateCommand.getAvatar())
                .changeProfile(adminUserUpdateCommand.getProfile())
                .changeUserRole(adminUserUpdateCommand.getUserRole());
    }


}