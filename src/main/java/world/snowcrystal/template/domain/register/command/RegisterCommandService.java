package world.snowcrystal.template.domain.register.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.register.entity.RegisterDomainService;
import world.snowcrystal.template.domain.user.repository.UserRepository;
import world.snowcrystal.template.domain.user.type.Account;
import world.snowcrystal.template.domain.user.type.Password;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterCommandService {

    private final UserRepository userRepository;
    private final RegisterDomainService registerDomainService;


    /**
     * 用户注册
     *
     * @return 新用户 id
     */
    @Transactional
    public RegisterResponse userRegister(RegisterCommand registerCommand) {
        Account account = Account.of(registerCommand.getAccount());
        Password password = Password.of(registerCommand.getPassword());
        Password checkPassword = Password.of(registerCommand.getCheckPassword());
        if (!password.equals(checkPassword)) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR, "两次输入的密码不一致");
        }
        synchronized (account.getValue().intern()) {
            User user = registerDomainService.createUser(account, password);
            user.setPassword(password);
            // 插入数据
            userRepository.save(user);
            return RegisterResponse.builder().userId(user.getId().getValue()).build();
        }
    }
}
