package world.snowcrystal.template.infrastructure.component;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import world.snowcrystal.template.domain.register.component.UserPasswordGenerator;
import world.snowcrystal.template.domain.user.type.Password;

@Component("userPasswordGenerator")
//@ConditionalOnMissingBean(UserPasswordGenerator.class)
public class DefaultUserPasswordGenerator implements UserPasswordGenerator {
    @Override
    public Password generate() {
        return new Password("123456214");
    }
}
