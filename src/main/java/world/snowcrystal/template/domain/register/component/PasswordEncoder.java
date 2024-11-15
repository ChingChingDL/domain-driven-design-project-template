package world.snowcrystal.template.domain.register.component;

import world.snowcrystal.template.domain.user.primitive.EncodedPassword;
import world.snowcrystal.template.domain.user.primitive.Password;



public interface PasswordEncoder {
    EncodedPassword encode(Password password);
}
