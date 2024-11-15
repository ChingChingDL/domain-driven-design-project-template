package world.snowcrystal.template.infrastructure.component;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import world.snowcrystal.template.domain.register.component.PasswordEncoder;
import world.snowcrystal.template.domain.user.type.Password;

@Component("passwordEncoder")
//@ConditionalOnMissingBean(PasswordEncoder.class)
public class DefaultPasswordEncoder implements PasswordEncoder {
    public static final String SALT = "TechnologyMillennium";

    // TODO PasswordEncoder 引入
    @Override
    public Password encode(Password password) {
        return new Password(DigestUtils.md5DigestAsHex((SALT + password).getBytes()));
    }
}
