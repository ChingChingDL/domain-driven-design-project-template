package world.snowcrystal.template.domain.register.component;

import world.snowcrystal.template.domain.user.type.Password;

public interface PasswordEncoder {
    Password encode(Password password);
}
