package world.snowcrystal.template.infrastructure.component;

import org.apache.commons.lang3.RandomStringUtils;
import world.snowcrystal.template.domain.register.component.UserPasswordGenerator;
import world.snowcrystal.template.domain.user.primitive.Password;

public class DefaultUserPasswordGenerator implements UserPasswordGenerator {
    @Override
    public Password generate() {
        return new Password(RandomStringUtils.randomAlphanumeric(16));
    }
}
