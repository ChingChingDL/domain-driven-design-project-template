package world.snowcrystal.template.domain.register.component;

import world.snowcrystal.template.domain.user.type.EncodedPassword;
import world.snowcrystal.template.domain.user.type.Password;



public interface PasswordEncoder {
    EncodedPassword encode(Password password);
}
