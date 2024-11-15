package world.snowcrystal.template.domain.register.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.entity.UserFactory;
import world.snowcrystal.template.domain.user.type.Account;
import world.snowcrystal.template.domain.user.type.Password;
import world.snowcrystal.template.domain.user.type.Username;

@Service
@RequiredArgsConstructor
public class RegisterDomainService {

    private final UserFactory userFactory;

    public User createUser(Username username) {
        return userFactory.create(username);
    }
    public User createUser(Account account, Password password){
        return userFactory.create(account, password);
    }


}
