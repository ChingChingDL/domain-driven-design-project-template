package world.snowcrystal.template.infrastructure.component;

import cn.hutool.crypto.digest.DigestUtil;
import world.snowcrystal.template.domain.register.component.PasswordEncoder;
import world.snowcrystal.template.domain.user.primitive.EncodedPassword;
import world.snowcrystal.template.domain.user.primitive.Password;


public class DefaultPasswordEncoder implements PasswordEncoder {
    public static final String SALT = "TechnologyMillennium";

    @Override
    public EncodedPassword encode(Password password) {
        return new EncodedPassword(this, DigestUtil.bcrypt(password.getValue() + SALT));
    }
}
