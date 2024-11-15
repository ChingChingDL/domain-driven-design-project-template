package world.snowcrystal.template.domain.user.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.register.component.PasswordEncoder;
import world.snowcrystal.template.domain.register.component.UserPasswordGenerator;
import world.snowcrystal.template.domain.register.component.UsernameGenerator;
import world.snowcrystal.template.domain.user.dto.command.UserCreateCommand;
import world.snowcrystal.template.domain.user.exception.AccountTakenException;
import world.snowcrystal.template.domain.user.repository.UserRepository;
import world.snowcrystal.template.domain.user.type.*;


/**
 * 不指定或者用户名密码的话，会自动生成哦
 *
 * @see UserPasswordGenerator
 * @see UsernameGenerator
 */
@Component
@RequiredArgsConstructor
public class UserFactory {

    private final UserRepository userRepository;
    private final UserPasswordGenerator passwordGenerator;
    private final UsernameGenerator usernameGenerator;
    private final PasswordEncoder passwordEncoder;


    public User create(Username username, Account account, Avatar avatar) {
        if (userRepository.checkExists(account)) {
            throw new AccountTakenException("账号已被占用");
        }
        return User.builder()
                .username(username)
                .account(account)
                .avatar(avatar)
                .password(passwordEncoder.encode(passwordGenerator.generate()))
                .build();
    }

    public User create(UserCreateCommand command) {
        return create(Username.of(command.getUsername()),
                Account.of(command.getAccount()),
                Avatar.of(command.getAvatar()));
    }


    public User create(Account account, Password password) {

        if (userRepository.checkExists(account)) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR, "账号已被占用");
        }
        return User.builder()
                .account(account)
                .username(usernameGenerator.generate())
                .password(passwordEncoder.encode(password))
                .build();
    }

    public User create(Account account, Password password, Username username) {
        if (userRepository.checkExists(account)) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR, "账号已被占用");
        }
        return User.builder()
                .account(account)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
    }

    public User create(Account account, Avatar avatar, String role) {
        if (userRepository.checkExists(account)) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR, "账号已被占用");
        }
        return User.builder()
                .account(account)
                .avatar(avatar)
                .role(Role.of(role))
                .username(usernameGenerator.generate())
                .password(passwordEncoder.encode(passwordGenerator.generate()))
                .build();
    }

    public User create(Username username) {

        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(passwordGenerator.generate()))
                .build();
    }

}
